package RedBlackTree;

import java.awt.*;

public class RedBlackTree {

    private Node root, NIL;
    private int length;
    String s ;
    public RedBlackTree() {
        length = 0;
        NIL = new Node();
        root = NIL;
    }

    public int getSize() {
        return length;
    }

    public Node getRoot() {
        return root;
    }

    public String format(String s){
        String a [] =s.split(" ");
        String S  = "";  
        for (String x: a){
            if(!x.equals("0")){
                S += x + " "; 
            }  
        }
        
        return S;
    }

    // print preOrder
    private void preOrder(Node tree) {
        if(tree != null) {
            s += tree.value + " ";
            preOrder(tree.left);
            preOrder(tree.right);
        }
    }

    public String preOrder() {
        s = "";
        preOrder(root);
        return format(s);
    }

    // print InOrder  
    private void inOrder(Node tree) {  
        if(tree != null) {
            inOrder(tree.left);
            s += tree.value + " ";
            inOrder(tree.right); 
        }
    }

    public String inOrder() {
        s = "";
        inOrder(root);
        return format(s);
    }

    //print postOrder
    private void postOrder(Node tree){
        if(tree != null){
            postOrder(tree.left);
            postOrder(tree.right);
            s += tree.value + " ";
        }
    }

    public String postOrder(){
        s = "";
        postOrder(root);
        return format(s);
    }
    // tìm min
    private Node minimumHelper(Node tree) {
        if (tree == NIL)
            return NIL;

        while(tree.left != NIL)
            tree = tree.left;
        return tree;
    }

    public int minimum() {
        Node Node = minimumHelper(root);
        if (Node != NIL)
            return Node.value;
        

        return 0;
    }

    // tìm max
    private Node maximumHelper(Node tree) {
        if (tree == NIL)
            return NIL;

        while(tree.right != NIL)
            tree = tree.right;
        return tree;
    }
    
    public int maximum() {
        Node Node = maximumHelper(root);
        if(Node != NIL)
            
            return Node.value;

        return 0;
    }

    
    private int getHeight(Node node, int leftHeight, int rightHeight, int max){

        // number of nodes from root to nil // nill not counted
        if(node == NIL){

            return 0; // to count edges returns -1

        }

        leftHeight = getHeight(node.left, leftHeight, rightHeight, max);
        rightHeight = getHeight(node.right, leftHeight, rightHeight, max);
        max = leftHeight > rightHeight ? leftHeight : rightHeight;

        return (1 + max);

    }
    public int getTreeHeight(Node node){

        //  In order not to initialize 3 variables in each recursion
        int leftHeight = 0;
        
        int rightHeight = 0;
        
        int max = 0;
        
        return (getHeight(node, leftHeight, rightHeight, max));
    }
    //------------------------------------------- Search-----------------------------------------------//
    public Node Search(int value) {
        Node currentNode = root;
        // khi giá trị không được tìm thấy và currentNode không phải là null
        while (currentNode != NIL && currentNode.value != value) { 
            if (value < currentNode.value){
                if(currentNode.left == null){
                    break;
                }else{
                    currentNode = currentNode.left; // trỏ sang cây con bên trái
                }
            }else {
                if(currentNode.right == null){
                    break;
                }else{
                    currentNode = currentNode.right; // trỏ sang cây con bên phải
                }
            }       
        }
        return currentNode; // giữ giá trị hoặc null
    }
    //-------------------------------------------------xoay trái---------------------------------------//
    private void leftRotate(Node currentNode) {
        Node y = currentNode.right;  // đặt y bằng con bên phải của currentNode
        currentNode.right = y.left; // biến cây con bên trái của y thành cây con của currentNode

        if (y.left != NIL)  
            y.left.parent = currentNode; // nếu tồn tại, đặt cây mẹ ở cây con bên trái là currentNode
       
        y.parent = currentNode.parent;  // đặt cha của y thành cha của currentNode

        if (currentNode.parent == NIL){
            root = y;
        }else if (currentNode.parent.left == currentNode){
            currentNode.parent.left = y;
        }else {
            currentNode.parent.right = y;
        }
        y.left = currentNode; // đặt currentNode bên trái của y
        currentNode.parent = y;
    }
    //-------------------------------------------------xoay phải---------------------------------------//
    private void rightRotate(Node currentNode) {
        Node y = currentNode.left; // đặt y là con trái của currentNode
        currentNode.left = y.right; // biến cây con bên phải của y thành cây con của currentNode

        if (y.right != NIL)
            y.right.parent = currentNode; // nếu tồn tại, hãy đặt cây mẹ ở cây con bên phải thành currentNode
        y.parent = currentNode.parent;  // đặt cha của y thành cha của currentNode

        if (currentNode.parent == NIL){
            root = y;
        }else if (currentNode.parent.left == currentNode){
            currentNode.parent.left = y;
        }else {
            currentNode.parent.right = y;
        }
        y.right = currentNode;// đặt currentNode ở bên phải của y 
        currentNode.parent = y;
    }

    private void transPlant(Node node, Node replacement) { 
        // thay thế nút bằng một nút khác
        if (node.parent == NIL) root = replacement;
        else if (node == node.parent.left) node.parent.left = replacement;
        else node.parent.right = replacement;
        replacement.parent = node.parent;
    }

    //--------------------------------------------------------- chèn -------------------------------------//
    private void insertFixUp(Node newNode) {
        // miễn là nó có màu đỏ
        while (newNode.parent.colorIsRed) {
            // nếu cây con là con bên trái của cây cha
            if (newNode.parent == newNode.parent.parent.left) {
                var y = newNode.parent.parent.right;
                // trường hợp 1, nếu màu của y là đỏ, hãy đặt y và cha của nó là màu đen và ông bà của nó là màu đỏ
                // sau đó di chuyển con trỏ đến hàm ông bà của nó cho lần lặp tiếp theo
                if (y.colorIsRed) {
                    newNode.parent.colorIsRed = false; // đen
                    y.colorIsRed = false; // đen
                    newNode.parent.parent.colorIsRed = true; // đỏ
                    newNode = newNode.parent.parent;
                } else {
                    // trường hợp 2, nếu newNode là con bên phải của cha của nó, hãy thực hiện xoay trái
                    // để cây con bên phải của cây này trống trước khi xoay phải
                    if (newNode == newNode.parent.right) {
                        newNode = newNode.parent;
                        leftRotate(newNode);
                    }
                    // trường hợp 3, thực hiện xoay bên phải và thay đổi màu để duy trì thuộc tính chiều cao màu đen
                    newNode.parent.colorIsRed = false; // đen
                    newNode.parent.parent.colorIsRed = true; // đỏ
                    rightRotate(newNode.parent.parent);
                }
            } else { 
                // đối xứng với trường hợp trên nhưng nếu cây con là con bên phải của cha mẹ của nó
                var y = newNode.parent.parent.left;
                if (y.colorIsRed) {
                    newNode.parent.colorIsRed = false;
                    y.colorIsRed = false;
                    newNode.parent.parent.colorIsRed = true;
                    newNode = newNode.parent.parent;
                } else {
                    if (newNode == newNode.parent.left) {
                        newNode = newNode.parent;
                        rightRotate(newNode);
                    }
                    newNode.parent.colorIsRed = false; // đen
                    newNode.parent.parent.colorIsRed = true; // đỏ
                    leftRotate(newNode.parent.parent);
                }
            }
        }
        root.colorIsRed = false;
    }

    public void insert(int value) {
        if (root == NIL) {  // nếu cây trống
            root = new Node(NIL);  // gán nút gốc cho giá trị này
            root.value = value;
            root.colorIsRed = false;
        } else {
            Node newNode = new Node(NIL);   // tạo node mới
            newNode.value = value;
            Node currentNode = root;
            Node currentParent = null;
            while (currentNode != NIL) { // di chuyển từ gốc để tìm vị trí newNode
                currentParent = currentNode; // nút đoạn giới thiệu cho cha của currentNode
                if (value < currentNode.value) currentNode = currentNode.left; // đi qua cây con bên trái
                else currentNode = currentNode.right; // đi qua cây con bên phải
            }
            newNode.parent = currentParent; // newNode cha là nút đoạn giới thiệu
            if (value < currentParent.value) currentParent.left = newNode;
            else currentParent.right = newNode;

            insertFixUp(newNode);
        }
        ++length;
    }

    //----------------------------------------------------- xóa ----------------------------------------//
    private void deleteFixUp(Node currentNode) {
        Node y;
        while (currentNode != root && !currentNode.colorIsRed) {
            if (currentNode == currentNode.parent.left) {
                y = currentNode.parent.right;
                if (y.colorIsRed) {
                    y.colorIsRed = false;
                    currentNode.parent.colorIsRed = true;
                    leftRotate(currentNode.parent);
                    y = currentNode.parent.right;
                }
                if (!y.left.colorIsRed && !y.right.colorIsRed) {
                    y.colorIsRed = true;
                    currentNode = currentNode.parent;
                } else {
                    if (!y.right.colorIsRed) {
                        y.left.colorIsRed = false;
                        y.colorIsRed = true;
                        rightRotate(y);
                        y = currentNode.parent.right;
                    }
                    y.colorIsRed = currentNode.parent.colorIsRed;
                    currentNode.parent.colorIsRed = false;
                    y.right.colorIsRed = false;
                    leftRotate(currentNode.parent);
                    currentNode = root;
                }
            } else {
                y = currentNode.parent.left;
                if (y.colorIsRed) {
                    y.colorIsRed = false;
                    currentNode.parent.colorIsRed = true;
                    rightRotate(currentNode.parent);
                    y = currentNode.parent.left;
                }
                if (!y.right.colorIsRed && !y.left.colorIsRed) {
                    y.colorIsRed = true;
                    currentNode = currentNode.parent;
                } else {
                    if (!y.left.colorIsRed) {
                        y.right.colorIsRed = false;
                        y.colorIsRed = true;
                        leftRotate(y);
                        y = currentNode.parent.left;
                    }
                    y.colorIsRed = currentNode.parent.colorIsRed;
                    currentNode.parent.colorIsRed = false;
                    y.left.colorIsRed = false;
                    rightRotate(currentNode.parent);
                    currentNode = root;
                }
            }
        }
        currentNode.colorIsRed = false;
    }
    // tìm node nhỏ nhất
    private Node getMinimumNode(Node currentNode) { 
        while (currentNode.left != NIL) { // trong khi cây con có một con bên trái
            currentNode = currentNode.left; // trỏ đến cây con đó
        }
        return currentNode; // trả về nút phía dưới bên trái nhất trong cây
    }

    public void remove(Node z) {
        if (z == null) return;
        Node x, y = z;
        boolean originalColor = z.colorIsRed;
        if (z.left == NIL) {
            x = z.right;
            transPlant(z, x);
        } else if (z.right == NIL) {
            x = z.left;
            transPlant(z, x);
        } else {
            y = getMinimumNode(z.right);
            originalColor = y.colorIsRed;
            x = y.right;
            if (y.parent == z) {
                x.parent = y;
            } else {
                transPlant(y, x);
                y.right = z.right;
                y.right.parent = y;
            }
            transPlant(z, y);
            y.left = z.left;
            y.left.parent = y;
            y.colorIsRed = z.colorIsRed;
        }
        if (!originalColor) deleteFixUp(x);
        --length;
    }

    private void print2DUtil(Node newNode, int space) {
        if (newNode == null)
            return;
        space += 10;
        print2DUtil(newNode.right, space);
        System.out.println();
        for (int i = 10; i < space; i++)
            System.out.print(" ");
        if (newNode.colorIsRed)
            System.out.println('R');
        else
            System.out.println('B');
        for (int i = 10; i < space; i++)
            System.out.print(" ");
        System.out.print(newNode.value);
        print2DUtil(newNode.left, space);
    }
    public void print2D() {
        print2DUtil(root, 0);
    }
    

    //-------------------------------------------- GUI --------------------------------------------------//
    private void traceTreePreorder(int x, int y, Node currentNode, Graphics g, int n, int level, int nodeDiameter) {
        if (currentNode == NIL || currentNode.value <0) {
            return;
        }
        
        int gap = (1000 / (n * nodeDiameter)) * Math.max(3, level);
        if(currentNode.left == NIL ){
            g.setColor(Color.BLACK);
            g.drawLine(x + 10, y + 5, x -  gap, y + 70);
            g.fillOval(x - gap - nodeDiameter / 2 + 10, y + 70 - nodeDiameter / 2 -5 , nodeDiameter, nodeDiameter);
            g.setColor(Color.white);
            g.drawString("NIL", x - gap, y +70);
        }
        if(currentNode.right == NIL){
            g.setColor(Color.BLACK);
            g.drawLine(x + 10, y + 5, x + gap + 30, y + 70);
            g.fillOval(x + gap +30 - nodeDiameter / 2 + 10, y + 70 - nodeDiameter / 2 -5 , nodeDiameter, nodeDiameter);
            g.setColor(Color.white);
            g.drawString("NIL", x + gap + 30, y + 70);
        }
        g.setColor(Color.black);
        if (currentNode.left != NIL) g.drawLine(x + 10, y + 5, x - gap, y + 70);
        if (currentNode.right != NIL) g.drawLine(x + 10, y + 5, x + gap + 30, y + 70);
        g.setColor((currentNode.colorIsRed) ? Color.red : Color.black);
        g.fillOval(x - nodeDiameter / 2 + 10, y - nodeDiameter / 2 -5 , nodeDiameter, nodeDiameter);
        g.setColor(Color.white);
        g.drawString(Integer.toString(currentNode.value), x, y);
        traceTreePreorder(x - gap, y + 70, currentNode.left, g, 2 * n, level - 1, nodeDiameter);
        traceTreePreorder(x + gap + 30, y + 70, currentNode.right, g, 2 * n, level - 1, nodeDiameter);

        
    }

    public void drawTree(Graphics g, int nodeDiameter) {
        int width = getDepth();
        this.traceTreePreorder(2500, 50, root, g, 1, width * 2, nodeDiameter);
    }
    private int getDepth(Node currentNode, int level) {
        if (currentNode == null) return level;
        return Math.max(getDepth(currentNode.left, level + 1),
                getDepth(currentNode.right, level + 1));
    }

    private int getDepth() {
        return getDepth(root, 0);
    }
}
