/**
 * Name: Yonglin Mai
 * Stony ID: 113299531
 * Homework 5
 * Recitation: 01
 */

/**
 * This is an exception that indicates the node has reach the max number of child.
 */
public class FullNodeException extends Exception{
    public FullNodeException() {
        super ("The node is full");
    }
}
