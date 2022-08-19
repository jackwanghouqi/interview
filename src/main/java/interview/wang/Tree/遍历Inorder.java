package interview.wang.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 左->中->右
 *
 * Solution1: 递归  O(n)
 *
 * Solution2: Iterative (Stack) O(n)
 *
 * Solution3: Morris Traversal (循环把所有的有左节点的root都放到左节点的右边最右，最终变成所有节点都在右边成为一条链) O(n)
 * */
public class 遍历Inorder {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        helper(root, res);
        return res;
    }

    public void helper(TreeNode root, List<Integer> res) {
        if(root != null) {
            helper(root.left, res);
            res.add(root.val);
            helper(root.right, res);
        }
    }

    public List<Integer> inorderTraversalII(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            res.add(curr.val);
            curr = curr.right;
        }
        return res;
    }

    public List<Integer> inorderTraversalIII(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        TreeNode curr = root;
        TreeNode pre;
        while (curr != null) {
            if (curr.left == null) {
                res.add(curr.val);
                curr = curr.right; // move to next right node
            } else { // has a left subtree
                pre = curr.left;
                while (pre.right != null) { // find rightmost
                    pre = pre.right;
                }
                pre.right = curr; // put cur after the pre node
                TreeNode temp = curr; // store cur node
                curr = curr.left; // move cur to the top of the new tree
                temp.left = null; // original cur left be null, avoid infinite loops
            }
        }
        return res;
    }
}
