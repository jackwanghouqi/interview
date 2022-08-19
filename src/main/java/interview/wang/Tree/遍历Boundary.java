package interview.wang.Tree;

/**
 * 【逆时钟打印边界】Anti-Clockwise
 * */
public class 遍历Boundary {
    void printBoundaryLeft(Node node) {
        if (node == null) return;
        if (node.left != null) {
            System.out.print(node.data + " ");
            printBoundaryLeft(node.left);
        } else if (node.right != null) {
            System.out.print(node.data + " ");
            printBoundaryLeft(node.right);
        }
    }
    void printLeaves(Node node) {
        if (node == null) return;
        printLeaves(node.left);
        if (node.left == null && node.right == null) System.out.print(node.data + " ");
        printLeaves(node.right);
    }
    void printBoundaryRight(Node node) {
        if (node == null) return;
        if (node.right != null) {
            printBoundaryRight(node.right);
            System.out.print(node.data + " ");
        } else if (node.left != null) {
            printBoundaryRight(node.left);
            System.out.print(node.data + " ");
        }
    }

    void printBoundary(Node node) {
        if (node == null) return;
        System.out.print(node.data + " ");
        printBoundaryLeft(node.left);
        printLeaves(node.left);
        printLeaves(node.right);
        printBoundaryRight(node.right);
    }

    // Driver program to test above functions
    public static void main(String args[])
    {
        遍历Boundary tree = new 遍历Boundary();
        Node node = new Node(20);
        node.left = new Node(8);
        node.left.left = new Node(4);
        node.left.right = new Node(12);
        node.left.right.left = new Node(10);
        node.left.right.right = new Node(14);
        node.right = new Node(22);
        node.right.right = new Node(25);
        tree.printBoundary(node);
    }
}
