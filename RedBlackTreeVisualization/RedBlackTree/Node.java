package RedBlackTree;

public class Node {

    public int value;
    public boolean colorIsRed;
    public Node parent, left, right;

    public Node() {
        colorIsRed = false;
        parent = left = right = null;
    }

    public Node(Node NIL) {
        colorIsRed = true;
        parent = left = right = NIL;
    }
   
}
