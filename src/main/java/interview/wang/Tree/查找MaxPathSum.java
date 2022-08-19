package interview.wang.Tree;

/*
* Given the root of a binary tree, return the maximum path sum of any non-empty path.
* 思路： 【每个节点】(加左右最大值)都有可能是最终的【max】
*       （1） 递归求左右的最大单线值
*       （2） 每个 node 求 左右【双线最大值】 和 左右【单线最大值】
*       （3）【双线最大值】直接比较 max, 【单线最大值】传递给parent计算用。
*
*   Time : O(N)   Space : O(H)
* */
public class 查找MaxPathSum {
    int max;
    public int maxPathSum(TreeNode root) {
        max = root.val;
        int rootMax = maxPathSumHelper(root);
        return Math.max(rootMax, max);
    }

    public int maxPathSumHelper(TreeNode root) {
        if(root == null) return 0;
        int left = Math.max(0, maxPathSumHelper(root.left));
        int right = Math.max(0, maxPathSumHelper(root.right));
        int sum = root.val + left + right;
        max = Math.max(sum, max);
        return left > right? root.val+ left : root.val+ right;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        System.out.println(new 查找MaxPathSum().maxPathSum(root));
    }
}

/*https://leetcode.com/problems/binary-tree-maximum-path-sum/*/