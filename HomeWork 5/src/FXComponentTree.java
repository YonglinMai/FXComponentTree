import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.Scanner;

/**
 * Name: Yonglin Mai
 * Stony ID: 113299531
 * Homework 5
 * Recitation: 01
 */

/**
 * A fully documented class called FXComponentTree which will serve as the tree manager for the FXComponentTree
 */
public class FXComponentTree {
    private FXTreeNode root;
    private FXTreeNode cursor;

    /**
     * Default constructor of the class, with all variables set to null.
     */
    public FXComponentTree(){
        root = null;
        cursor = null;
    }

    /**
     * A getter method that gets the cursor of the FXComponentTree.
     * @return          :
     *                  Returns the cursor of the FXComponentTree.
     */
    public FXTreeNode getCursor() {
        return cursor;
    }

    /**
     *  A setter method that sets the root of the FXComponentTree.
     * @param root          :
     *                      The root of the FXComponentTree.
     */
    public void setRoot(FXTreeNode root) {
        this.root = root;
    }

    /**
     * A getter method that gets the root of the FXComponentTree.
     * @return              :
     *                      The root of the FXComponentTree.
     */
    public FXTreeNode getRoot() {
        return root;
    }

    /**
     * Sets the cursor to the root of the FXComponentTree
     */
    public void cursorToRoot(){
        cursor = root;
    }

    /**
     * Removes the child at the specified index of the FXComponentTree, as well as all of its children.
     * @param index         :
     *                      the index of the children.
     */
    public void deleteChild(int index){
        if (index >= cursor.checkChildNull() && cursor.checkChildNull() != -1)
            throw new ArrayIndexOutOfBoundsException();
        if (index < 0)
            throw new IllegalArgumentException();

        for (int i = index; i < cursor.getChildrenCount(); i++) {
            cursor.getChildren()[i].setPosition(cursor.getChildren()[i].getPosition() - 1);
            cursor.getChildren()[i] = cursor.getChildren()[i + 1];
        }
        cursor.setChildrenCount(cursor.getChildrenCount() - 1);

    }

    /**
     * Adds the given FXTreeNode to the corresponding index of the children array.
     * @param index             :
     *                          The index of the FXTreeNode
     * @param node              :
     *                          The FXTreeNode to be added
     * @throws EmptyHoleException           :
     *                                      Throw EmptyHoleException to indicate a empty hole in the child array.
     * @throws FullNodeException            :
     *                                      Throw FullNodeException to indicate the child array is full.
     */
    public void addChild(int index, FXTreeNode node) throws EmptyHoleException, FullNodeException {
        if (index > cursor.checkChildNull())
            throw new EmptyHoleException();
        if (cursor.checkChildNull() == -1)
            throw new FullNodeException();
        if (index < 0)
            throw new IllegalArgumentException();

        for (int i = cursor.childrenCount; i > index; i--) {
            //cursor.getChildren()[i].setPosition(cursor.getChildren()[i].getPosition() + 1);
            cursor.getChildren()[i] = cursor.getChildren()[i - 1];
            cursor.getChildren()[i].setPosition(cursor.getChildren()[i].getPosition() + 1);
        }
        cursor.getChildren()[index] = node;
        node.setParent(cursor);
        node.setPosition(index);
        cursor.setChildrenCount(cursor.getChildrenCount() + 1);
    }

    /**
     * Sets the current FXTreeNode’s text to the specified text.
     * @param text          :
     *                      The new text to be replaced with.
     */
    public void setTextAtCursor(String text){
        cursor.setText(text);
    }

    /**
     * Moves the cursor to the child FXTreeNode of the of the cursor corresponding to the specified index.
     * @param index            :
     *                         The index of the child.
     */
    public void cursorToChild(int index){
        if (cursor.getChildrenCount() != 0 && index < cursor.getChildrenCount())
            cursor = cursor.getChildren()[index];
    }

    /**
     * Moves the cursor to the parent of the current FXTreeNode.
     */
    public void cursorToParent(){
        cursor = cursor.getParent();
    }

    /**
     * Compares the the user input to the existing ComponentType.
     * @param enumString        :
     *                          The user input
     * @return                  :
     *                          The corresponding ComponentType.
     */
    public static ComponentType compareComponent(String enumString){
        return switch (enumString.toUpperCase()) {
            case "BUTTON" -> ComponentType.Button;
            case "LABEL" -> ComponentType.Label;
            case "TEXTAREA" -> ComponentType.TextArea;
            case "HBOX" -> ComponentType.HBox;
            case "VBOX" -> ComponentType.VBox;
            case "ANCHORPANE" -> ComponentType.AnchorPane;
            default -> null;
        };
    }

    /**
     * Takes a string array of ints and convert into int array.
     * @param position          :
     *                          string array
     * @return                  :
     *                          Int array.
     */
    public static int[] positionArray(String[] position){
        int[] intPos = new int[position.length];
        for (int i = 0; i < position.length; i++){
            intPos[i] = Integer.parseInt(position[i]);
        }
        return intPos;
    }

    /**
     * Inserting FXTreeNode from the file read.
     * @param tree              :
     *                          The FXComponentTree object being modified.
     * @param position          :
     *                          the position of the FXTreeNode.
     * @param node              :
     *                          The FXTreeNode to be inserted.
     * @throws EmptyHoleException               :
     *                                          throw EmptyHoleException to indicate that the addition of the new node will create a hole in the tree.
     * @throws FullNodeException                :
     *                                          throw FullNodeException to indicate that the child array of the node is full.
     */
    public static void insertNode (FXComponentTree tree, int[] position, FXTreeNode node) throws EmptyHoleException, FullNodeException {
        if (position.length == 1){
            tree.setRoot(node);
            tree.cursorToRoot();

        }
        else if (position.length == 2){
            tree.cursorToRoot();
            tree.addChild(position[position.length - 1], node);
        }
        else{
            tree.cursorToRoot();
            for (int i = 1; i < position.length - 1; i++){
                tree.cursorToChild(position[i]);
            }
            tree.addChild(position[position.length-1], node);
            node.setPosition(position[position.length-1]);
            node.setParent(tree.cursor);
        }

    }

    /**
     * Extract int from a string to represent the position of the FXTreeNode.
     * @param string            :
     *                          The position of the FXTreeNode in string form.
     * @return                  :
     *                          The int array of the position of the FXTreeNode.
     */
    public static int[] extractInt(String string){
        String numInStr = string.replaceAll("[^0-9]+", " ");
        String[] strArr = numInStr.split(" ");
        return positionArray(strArr);
    }

    /**
     * Reading and extracting information from the file.
     * @param fileName          :
     *                          The name of the file
     * @return                  :
     *                          A FXComponentTree with the information from the file.
     * @throws EmptyHoleException           :
     *                                      throw EmptyHoleException to indicate that the addition of the new FXTreeNode will create a hole in the tree.
     * @throws FullNodeException            :
     *                                      throw FullNodeException to indicate that the child array of the FXTreeNode is full.
     * @throws FileNotFoundException        :
     *                                      throw FileNotFoundException to indicate that the file can not be located.
     */
    public static FXComponentTree readFromFile(String fileName) throws EmptyHoleException, FullNodeException, FileNotFoundException {
        FXComponentTree tree = new FXComponentTree();

        File filename = new File(fileName);

        Scanner currentFile = new Scanner(filename);

        while (currentFile.hasNextLine()){
            String line = currentFile.nextLine();
            String[] splitLine = line.split(" ", 3);

            String position = splitLine[0];
            int[] posArray = extractInt(position);

            String enumString =  splitLine[1];
            ComponentType component = compareComponent(enumString);

            FXTreeNode newNode = new FXTreeNode();
            newNode.setType(component);

            if(splitLine.length == 3){
                String newText = splitLine[2];
                newNode.setText(newText);
            }

            insertNode(tree, posArray,newNode);
        }
        currentFile.close();

        return tree;
    }

    /**
     * Creates a int array of the position of the FXTreeNode.
     * @param node          :
     *                      The FXTreeNode that is to be located.
     * @return              :
     *                      int array of the position of the FXTreeNode.
     */
    public int[] positionArr(FXTreeNode node){
        if(node.getParent() == null)
            return new int[]{0};
        else{
            int[] currentArray = new int[]{node.position};
            int[] nextArray = positionArr(node.getParent());
            int[] finalArray = (int[]) Array.newInstance(currentArray.getClass().getComponentType(),
                                                            currentArray.length + nextArray.length);
            System.arraycopy(nextArray, 0, finalArray, 0, nextArray.length);
            System.arraycopy(currentArray, 0, finalArray, nextArray.length, currentArray.length);
            return finalArray;
        }
    }

    /**
     * Create a string version of the FXTreeNode position.
     * @param node          :
     *                      the target FXTreeNode.
     * @return              :
     *                      The position of the FXTreeNode in string form.
     */
    public String positionToText(FXTreeNode node){
        int[] position = positionArr(node);
        String positionStr = "";
        for (int i:position){
            positionStr = positionStr + "-" + i;
        }
        return positionStr;
    }

    /**
     * Convert the FXComponentTree into a string array.
     * @param node          :
     *                      The starting FXTreeNode.
     * @return              :
     *                      A string array of the FXComponentTree.
     */
    public String[] traverseTree(FXTreeNode node){
        if(node.getChildrenCount() == 0)
            return new String[]{positionToText(node) + " " + node.toString()};
        else{
            String[] currentString = new String[]{positionToText(node) + " " + node.toString()};
            for (int i = 0; i < node.getChildrenCount(); i++){
                String[] nextString = traverseTree(node.getChildren()[i]);
                String[] newArray = (String[]) Array.newInstance(currentString.getClass().getComponentType(),
                                                            currentString.length + nextString.length);
                System.arraycopy(currentString,0,newArray,0, currentString.length);
                System.arraycopy(nextString,0,newArray,currentString.length, nextString.length);
                currentString = newArray;
            }

            return currentString;
        }
    }

    /**
     * convert the information of a FXComponentTree to a file
     * @param tree          :
     *                      The FXComponentTree that is being converted
     * @param fileName      :
     *                      The file to write the information in.
     * @throws FileNotFoundException        :
     *                                      throw FileNotFoundException to indicate that the file can not be located.
     */
    public static void writeToFile(FXComponentTree tree, String fileName) throws FileNotFoundException {
        String[] stringArray = tree.traverseTree(tree.getRoot());
        File file = new File(fileName);
        PrintWriter pw = new PrintWriter(file);
        for (String s : stringArray) pw.println(s);

        pw.close();
    }

    /**
     * Create string with spacing corresponding to the position of the FXTreeNode
     * @param node          :
     *                      The target FXTreeNode
     * @return              :
     *                      The string version of the FXTreeNode.
     */
    public String spacingHelper(FXTreeNode node){
        int[] arr = positionArr(node);
        if(cursor == node)
            return  "          ".repeat(arr.length - 1) + "==>";
        else
            return  "          ".repeat(arr.length - 1) + "+--";
    }
    public String[] treeToStr(FXTreeNode node){
        if(node.getChildrenCount() == 0)
            return new String[]{spacingHelper(node) + node.toString()};
        else{
            String[] currentString = new String[]{spacingHelper(node) + node.toString()};
            for (int i = 0; i < node.getChildrenCount(); i++){
                String[] nextString = treeToStr(node.getChildren()[i]);
                String[] newArray = (String[]) Array.newInstance(currentString.getClass().getComponentType(),
                        currentString.length + nextString.length);
                System.arraycopy(currentString,0,newArray,0, currentString.length);
                System.arraycopy(nextString,0,newArray,currentString.length, nextString.length);
                currentString = newArray;
            }

            return currentString;
        }
    }

    /**
     * Generate a string form of the FXComponentTree.
     * @return          :
     *                  FXComponentTree in string form.
     */
    public String toString(){
        String s = "";
        String[] sArray = treeToStr(this.root);
        for(String str: sArray){
            s += str;
        }

        return s;
    }
}
