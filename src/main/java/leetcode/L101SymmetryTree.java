package leetcode;

/**
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
 * 
 * left and right look like a mirror for each other
 * */

public class L101SymmetryTree {

	public static void main(String[] args) {
		

	}
	
	
	public static int isSymmetric(TreeNode a) {
	    return isMirror(a, a);
	}
	
	public static int isMirror(TreeNode a1, TreeNode a2) {
	    if(a1 == null && a2 == null) return 1;
	    if(a1 == null || a2 == null) return 0;
        return (a1.val==a2.val?1:0) * isMirror(a1.left, a2.right) * isMirror(a2.left, a1.right);
	}
	
	// Definition for binary tree
	static class TreeNode {
	 int val;
	 TreeNode left;
	 TreeNode right;
	 TreeNode(int x) { val = x; }
	 }

}
