/**
 * Name: Yonglin Mai
 * Stony ID: 113299531
 * Homework 5
 * Recitation: 01
 */

/**
 *
 */

/**
 * Write a fully documented class called FXTreeNode which holds the type of component being represented,
 * an array of children (null if this will be a Control), and string for the text (null if this is a Container)
 */
public class FXTreeNode {
    private String text;
    private ComponentType type;
    private FXTreeNode parent;
    private FXTreeNode[] children;
    final int maxChildren = 10;
    int childrenCount;
    int position;

    /**
     * The constructor of the class.
     */
    public FXTreeNode(){
        text = "";
        parent = null;
        children = new FXTreeNode[maxChildren];
        childrenCount = 0;
        position = 0;
    }

    /**
     * This is a getter method that gets the position of the FXTreeNode
     * @return          :
     *                  The position of the FXTreeNode.
     */
    public int getPosition() {
        return position;
    }

    /**
     * This is a setter method that sets the position of the FXTreeNode
     * @param position          :
     *                          The position fo the FXTreeNode
     */
    public void setPosition(int position) {
        this.position = position;
    }

    /**
     * This is a setter method that sets the counter of the number of child in of the FXTreeNode
     * @param childrenCount         :
     *                              The number of child.
     */
    public void setChildrenCount(int childrenCount) {
        this.childrenCount = childrenCount;
    }

    /**
     *  This is a getter method that gets the number of child of the FXTreeNode
     * @return                  :
     *                          The number of child.
     */
    public int getChildrenCount() {
        return childrenCount;
    }

    /**
     * This is a setter method tha text the text of the FXTreeNode
     * @param text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * This is a setter method that sets the ComponentType of the FXTreeNode
     * @param type              :
     *                          the ComponentType of the FXTreeNode
     */
    public void setType(ComponentType type) {
        this.type = type;
    }

    /**
     * This is a getter method that gets the parent of the FXTreeNode
     * @return                  :
     *                          The parent of the FXTreeNode
     */
    public FXTreeNode getParent() {
        return parent;
    }

    /**
     * This is a setter method that sets the parent of the FXTreeNode
     * @param parent            :
     *                          The parent FXTreeNode of the current FXTreeNode
     */
    public void setParent(FXTreeNode parent) {
        this.parent = parent;
    }

    /**
     * This is a getter method that gets the children of the FXTreeNode
     * @return              :
     *                       The children of the FXTreeNode
     */
    public FXTreeNode[] getChildren() {
        return children;
    }

    /**
     * This is a setter method that sets the children of the FXTreeNode
     * @param children          :
     *                          The children of the FXTreeNode.
     */
    public void setChildren(FXTreeNode[] children) {
        this.children = children;
    }

    /**
     * This is a getter method that gets the max number of child of the FXTreeNode
     * @return                  :
     *                          The limit of children of the FXTreeNode.
     */
    public int getMaxChildren() {
        return maxChildren;
    }


    /**
     * Check if the children array has any empty holes.
     * @return                  :
     *                          The first occurrence of an empty hole.
     *                          -1 if the child array is full.
     */
    public int checkChildNull(){
        for (int i = 0; i < maxChildren; i++){
            if (children[i] ==  null){
                return i;
            }
        }
        return -1;
    }

    /**
     * Generates a string form of the node.
     * @return              :
     *                      The string form of the node.
     */
    public String toString(){
        return type + " " + text + "\n";
    }
}
