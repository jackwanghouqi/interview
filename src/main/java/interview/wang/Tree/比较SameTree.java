package interview.wang.Tree;

import java.util.ArrayDeque;

/**
 * Solution1: 递归  O(n)
 *
 * Solution2: Deque (double-ended queue)
 * */
public class 比较SameTree {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        // 同时null
        if (p == null && q == null) return true;
        // 有一个null
        if (q == null || p == null) return false;
        if (p.val != q.val) return false;
        return isSameTree(p.right, q.right) &&
                isSameTree(p.left, q.left);
    }

    public boolean isSameTreeII(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (!equalNode(p, q)) return false;

        // init deques
        ArrayDeque<TreeNode> deqP = new ArrayDeque<TreeNode>();
        ArrayDeque<TreeNode> deqQ = new ArrayDeque<TreeNode>();
        deqP.addLast(p);
        deqQ.addLast(q);

        while (!deqP.isEmpty()) {
            p = deqP.removeFirst();
            q = deqQ.removeFirst();

            if (!equalNode(p, q)) return false;
            if (p != null) {
                if (!equalNode(p.left, q.left)) return false;
                if (p.left != null) {
                    deqP.addLast(p.left);
                    deqQ.addLast(q.left);
                }
                if (!equalNode(p.right, q.right)) return false;
                if (p.right != null) {
                    deqP.addLast(p.right);
                    deqQ.addLast(q.right);
                }
            }
        }
        return true;
    }

    public boolean equalNode(TreeNode p, TreeNode q) {
        // p and q are null
        if (p == null && q == null) return true;
        // one of p and q is null
        if (q == null || p == null) return false;
        if (p.val != q.val) return false;
        return true;
    }

}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
