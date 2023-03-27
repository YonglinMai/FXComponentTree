/**
 * Name: Yonglin Mai
 * Stony ID: 113299531
 * Homework 5
 * Recitation: 01
 */

/**
 * This is a fully documented exception that indicates that the addition of node will create a hole in the tree.
 */
public class EmptyHoleException extends Exception{
    public EmptyHoleException(){
        super ("This will create a Empty hole");
    }
}
