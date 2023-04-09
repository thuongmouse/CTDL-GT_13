package RedBlackTree;

public class Test {
    public static void main(String[] args){
        RedBlackTree tree = new RedBlackTree();
        // chèn thêm phần tử vào cây
        tree.insert(3);
        tree.insert(30);
        tree.insert(20);
        tree.insert(45);
        tree.insert(50);
        tree.remove(tree.Search(30));
        tree.print2D();
        System.out.println();
        System.out.println("Max:" + tree.maximum());
        System.out.println("Min:"+ tree.minimum());
        System.out.println("In Order : " + tree.inOrder());
        System.out.println("Pre Order : " + tree.preOrder());
        System.out.println("Post Order : " + tree.postOrder());
        System.out.println("Height of node 20 :" + tree.getTreeHeight(tree.Search(20)));
        
       
    }
}
