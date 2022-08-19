package interview.wang.Tree;

import java.util.HashSet;
import java.util.Vector;

public class 比较Compare {

    //【利用HashSet】
    static boolean checkBSTs(Node root1, Node root2) {
        // 基本情况 base cases
        if (root1 != null && root2 != null)
            return true;
        if ((root1 == null && root2 != null) || (root1 != null && root2 == null))
            return false;

        //插入集合
        HashSet<Integer> s1 = new HashSet<Integer>();
        HashSet<Integer> s2 = new HashSet<Integer>();
        insertToHash(root1, s1);
        insertToHash(root2, s2);

        // 比较两个集合
        return (s1.equals(s2));
    }

    // function to insert elements of the tree to map m
    static void insertToHash(Node root, HashSet<Integer> s) {
        if (root == null)
            return;
        insertToHash(root.left, s);
        s.add(root.data);
        insertToHash(root.right, s);
    }

    //【利用Vector】 比较Vector Hashcode????
    static boolean checkBSTsII(Node root1, Node root2)
    {
        // Base cases
        if (root1 == null && root2 == null)
            return true;
        if ((root1 == null && root2 != null)
                || (root1 != null && root2 == null))
            return false;

        //插入集合
        Vector<Integer> v1 = new Vector<Integer>();
        Vector<Integer> v2 = new Vector<Integer>();
        storeInorder(root1, v1);
        storeInorder(root2, v2);

        // 比较两个集合
        return (v1.hashCode() == v2.hashCode());//????? hashcode??????
    }

    static void storeInorder(Node root, Vector<Integer> v)
    {
        if (root == null)
            return;
        storeInorder(root.left, v);
        v.add(root.data);
        storeInorder(root.right, v);
    }

    static Node newNode(int val) {
        Node temp = new Node();
        temp.data = val;
        temp.left = temp.right = null;
        return temp;
    }
}

class Node {
    public Node(){}
    public Node(int data){this.data = data;}
    public int data;
    public Node left;
    public Node right;
};