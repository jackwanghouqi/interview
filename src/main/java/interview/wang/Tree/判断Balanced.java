package interview.wang.Tree;

public class 判断Balanced {

    /*★【Bottom-up recursion approach】 Time : O(n)  Space O(n) --(if all tree is skewed) recursion stack=n*/
    public boolean isBalanced(TreeNode root) {
        if(root == null) return true;
        return Math.abs(height(root.left)-height(root.right))  <2 && isBalanced(root.left)&& isBalanced(root.right);
    }

    private TreeInfo loadAndCheckTreeInfo(TreeNode root) {
        if(root == null) return new TreeInfo(-1, true);
        TreeInfo left = loadAndCheckTreeInfo(root.left);
        if(!left.balanced) return new TreeInfo(-1, false);
        TreeInfo right = loadAndCheckTreeInfo(root.right);
        if(!right.balanced) return new TreeInfo(-1, false);

        return Math.abs(left.height-right.height)<2? new TreeInfo(Math.max(left.height, right.height), true) : new TreeInfo(-1, false);
    }

    /*★【Top-down recursion approach】  Space O(n) --(if all tree is skewed) recursion stack=n*/
    /*
    *  Time : worst O(nlogn) --tree (if no early stop, O(n^2))   best O(n) --skewed only check the height for the first two subtrees.
    *  Space O(n) --(if all tree is skewed) recursion stack=n
    * */
    public boolean isBalancedII(TreeNode root) {
        if(root == null) return true;
        return Math.abs(height(root.left)-height(root.right))  <2 && isBalanced(root.left)&& isBalanced(root.right);
    }
    private int height(TreeNode root) {
        if(root==null) return -1;
        return 1+ Math.max(height(root.left), height(root.right));
    }
}

class TreeInfo {
    public int height;
    public boolean balanced;
    public TreeInfo(int height, boolean balanced) {
        this.height = height;
        this.balanced = balanced;
    }
}

/*https://leetcode.com/problems/balanced-binary-tree/*/