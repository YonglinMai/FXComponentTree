
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Write a fully-documented class named FXGuiMaker that takes in a text file, generates a FXComponentTree and provides an interface for a user to manipulate the tree.
 */
public class FXGuiMaker {
    static FXComponentTree tree = new FXComponentTree();
    private static final String menu = """
            Menu:\s
            L- Load from file
            P- Print
            C- Cursor to child (index number)
            A- Add child (index, type, prompt for text)
            U- Cursor up (to parent)
            E- Edit Text
            D- Delete child (index number)
            R- Cursor to root
            S- Save to Text File
            Q - Quit
            """
    ;

    /**
     * Checks if the user has input a valid text
     * @return          :
     *                  The string form of the text entered.
     */
    public static String checkText(){
        Scanner input = new Scanner(System.in);

        System.out.println(" Please enter text: ");
        String text = input.nextLine();

        while (text.isEmpty()){
            System.out.println(" Please enter valid text: ");
            text = input.nextLine();
        }

        return text;
    }

    /**
     * Compose a FXTreeNode that is to be added to the current tree
     * @return              :
     *                      FXTreeNode with the information from the user.
     */
    public static FXTreeNode addNode(){
        Scanner input = new Scanner(System.in);

        System.out.println("Select component type (H - HBox, V - VBox, T - TextArea, B - Button, L - Label): ");
        String type = input.nextLine();

        FXTreeNode node = new FXTreeNode();

        while (!type.equalsIgnoreCase("H") &&
                !type.equalsIgnoreCase("V") &&
                !type.equalsIgnoreCase("T") &&
                !type.equalsIgnoreCase("B") &&
                !type.equalsIgnoreCase("L")){
            System.out.println("Please enter valid type: ");
            type = input.nextLine();
        }

        switch (type.toUpperCase()){
            case "H":
                node.setType(ComponentType.HBox);
                break;
            case "V":
                node.setType(ComponentType.VBox);
                break;
            case "T":
                node.setType(ComponentType.TextArea);
                node.setText(checkText());
                break;
            case "B":
                node.setType(ComponentType.Button);
                node.setText(checkText());
                break;
            case "L":
                node.setType(ComponentType.Label);
                node.setText(checkText());
                break;
        }

        return node;
    }

    /**
     * Main method that runs the entire stimulation.
     */
    public static void main(String[] args) {
        String option;
        do {
            System.out.println(menu);
            Scanner input = new Scanner(System.in);

            System.out.println("Please select an option: ");
            option = input.nextLine();
            switch (option.toUpperCase()){
                case "L" :
                    try{
                        System.out.println("Please enter filename: ");
                        String fileName = input.nextLine();
                        tree =FXComponentTree.readFromFile(fileName);
                        tree.cursorToRoot();
                    }catch (EmptyHoleException | FullNodeException e){
                        System.out.println(e.getMessage());
                    }catch (FileNotFoundException e){
                        System.out.println("File not found");
                    }

                    break;
                case "P" :
                    System.out.println(tree.toString());
                    break;
                case "C" :
                    System.out.println("Please enter number of child (starting with 1): ");
                    int num = input.nextInt();
                    tree.cursorToChild(num-1);
                    break;
                case "A" :
                    try{
                        FXTreeNode newNode = addNode();
                        System.out.println("Please enter an index: ");
                        int index = input.nextInt();
                        tree.addChild(index - 1, newNode);

                    }catch (EmptyHoleException | FullNodeException | IllegalArgumentException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case "U" :
                    tree.cursorToParent();
                    break;
                case "E" :
                    System.out.println("Please enter new text: ");
                    String text = input.nextLine();

                    tree.setTextAtCursor(text);

                    break;
                case "D" :
                    System.out.println("Please enter number of child (starting with 1): ");
                    int index = input.nextInt();

                    tree.deleteChild(index - 1);

                    break;
                case "R" :
                    tree.cursorToRoot();
                    break;
                case "S" :
                    try{
                        System.out.println("Please enter a filename: ");
                        String fileName = input.nextLine();

                        FXComponentTree.writeToFile(tree, fileName);

                    }catch(FileNotFoundException e){
                        System.out.println("File not found");
                    }
                    break;
                case "Q" :
                    System.out.println("Make like a tree and leave!");
                    break;
            }
        } while (!option.equalsIgnoreCase("Q"));

    }
}
