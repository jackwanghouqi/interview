package interview.wang.Tree;

import java.util.HashSet;
import java.util.LinkedList;

public class 查找BST {
    /*★【LCA最短祖辈】【Recursion】Time:  O(N)    Space: O(N) stack */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root.val < p.val && root.val < q.val) return lowestCommonAncestor(root.right, p,q);
        else if(root.val > p.val && root.val > q.val) return lowestCommonAncestor(root.left, p,q);
        else return root;
    }

    /*★LCA最短祖辈】【Iterative】Time:  O(N)    Space: O(1) */
    public TreeNode lowestCommonAncestorII(TreeNode root, TreeNode p, TreeNode q) {
        while(root!= null) {
            if(root.val> p.val && root.val > q.val) root = root.left;
            else if(root.val < p.val && root.val < q.val) root = root.right;
            else return root;
        }
        return null;
    }

    /*★★【Validate BST Binary Search Tree】  O(n)*/
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    public boolean isValidBST(TreeNode root, long min, long max) {
        if(root == null) return true;
        if(root.val >= max|| root.val <= min) return false;
        return isValidBST(root.left, min, root.val) && isValidBST(root.right, root.val, max);
    }

    /*★★【Kth Smallest Element in a BST】 有序 O(H+k) */
    public int kthSmallestBST(TreeNode root, int k) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        while (true) {//TODO 继续消化
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (--k == 0) return root.val;
            root = root.right;
        }
    } /*https://leetcode.com/problems/kth-smallest-element-in-a-bst/*/

    /*★★【Kth Smallest Element in a BT】 无序 O(n)*/
    public int kthSmallestBT(TreeNode root, int k) {
        HashSet<Integer> set = new HashSet<>(); //【如果BST】 用List
        traversal(root, set, k);
        return (int)set.toArray()[k-1];
    }

    private void traversal(TreeNode node, HashSet<Integer> list, int k) {
        if(node == null) return;
        traversal(node.left, list, k);
        list.add(node.val);//【如果BST】 加判断==> if(--k == -1) return;
        traversal(node.right, list, k);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(1);
        root.right = new TreeNode(5);
        //root.right.left = new TreeNode(3);
        root.right.right = new TreeNode(3);
        System.out.println(new 查找BST().kthSmallestBST(root, 2));
    }
}
