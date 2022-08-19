package interview.wang.Tree;

import java.util.Vector;

/**
 * Given a BST (Binary Search Tree) that may be unbalanced,
 * convert it into a 【balanced BST】 that has minimum possible height.
 * */
public class 转换BSTto平衡 {
    Node balance(Node root) {
        Vector<Node> nodes = new Vector<Node>();
        storeBSTNodes(root, nodes);
        int n = nodes.size();
        return build(nodes, 0, n - 1);
    }

    Node build(Vector<Node> nodes, int start, int end) {
        if (start > end) return null; // base case
        int mid = (start + end) / 2; //get mid
        Node node = nodes.get(mid);
        node.left = build(nodes, start, mid - 1);//build left tree
        node.right = build(nodes, mid + 1, end);//build right tree
        return node;
    }

    void storeBSTNodes(Node root, Vector<Node> nodes) {
        if (root == null) return; // Base case
        storeBSTNodes(root.left, nodes);
        nodes.add(root);
        storeBSTNodes(root.right, nodes);
    }

    public static void main(String[] args) {
        转换BSTto平衡 tool = new 转换BSTto平衡();
        Node node = new Node(10); node.left = new Node(8);
        node.left.left = new Node(7); node.left.left.left = new Node(6);
        node.left.left.left.left = new Node(5);

        node = tool.balance(node);
        System.out.println("balanced BST is :"); tool.preOrderPrint(node);}
    void preOrderPrint(Node node){if(node == null)return;System.out.print(node.data + " ");
        preOrderPrint(node.left);preOrderPrint(node.right);}
}
