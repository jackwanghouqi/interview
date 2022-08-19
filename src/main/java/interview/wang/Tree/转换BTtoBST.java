package interview.wang.Tree;

import java.util.Arrays;

/**
 * Given a Binary Tree, convert it to a Binary Search Tree
 * 【思路】
 *  (1) 结构不变
 *  (2) 顺序改变
 *  (3) 排序插入
 *  (4) store and sort in an array
 *
 * */
public class 转换BTtoBST {

    static void binaryTreeToBST(Node root) {
        // base case
        if (root == null) return;
        //count nodes
        int n = countNodes(root);
        // 【store in array】
        int arr[] = new int[n];
        store(root, arr, 0);
        // 【Sort array】
        Arrays.sort(arr);
        // 【Copy back to Binary Tree】
        arrayToBST(arr, root,0);
    }

    /*Copy node to an array*/
    static int store(Node node, int inorder[], int index) {
        // Base Case
        if (node == null) return index;
        //【Left】
        index = store(node.left, inorder, index);
        //【Root】
        inorder[index++] = node.data;
        //【Right】
        return store(node.right, inorder, index);
    }

    /*Copy array to a Binary search tree*/
    static int arrayToBST(int[] arr, Node root, int index) {
        // Base Case
        if (root == null) return index;
        //【Left】
        index = arrayToBST(arr, root.left, index);
        //【Root】
        root.data = arr[index++];
        //【Right】
        return arrayToBST(arr, root.right, index);
    }

    //helper
    static int countNodes(Node root) {
        if (root == null) return 0;
        return 1+countNodes(root.left)+countNodes(root.right);
    }

    public static void main(String args[]) {
        Node root = newNode(10); root.left = newNode(30);
        root.right = newNode(15); root.left.left = newNode(20);
        root.right.right = newNode(5);
        binaryTreeToBST(root);
        System.out.println("converted BST: "); printInorder(root);

    }

    static void printInorder(Node node) {if(node==null) return;printInorder(node.left);
        System.out.print(node.data+" "); printInorder(node.right);}
    static Node newNode(int data) { Node temp = new Node();
        temp.data = data;temp.left = null;temp.right = null;return temp;}
}

/*
* https://www.geeksforgeeks.org/binary-tree-to-binary-search-tree-conversion/
* */