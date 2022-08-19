package interview.wang.Tree;

import java.util.*;
/**
 * O(N) O(N)
 * */
public class 遍历Level {
    //★★【全部】 O(N) O(N)
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();
        if(root == null) return result;
        Deque<TreeNode> currentLevel = new LinkedList<>(){{add(root);}};
        while(!currentLevel.isEmpty()) {
            ArrayList<Integer> list = new ArrayList<>(currentLevel.size());
            result.add(list);
            Deque<TreeNode> nextLevel = new LinkedList<>();
            while(!currentLevel.isEmpty()) {
                TreeNode node = currentLevel.removeFirst();
                list.add(node.val);
                if(node.left != null) nextLevel.add(node.left);
                if(node.right != null) nextLevel.add(node.right);
            }
            if(!nextLevel.isEmpty()) currentLevel = nextLevel;
        }
        return result;
    }

    //★★【最右边视角】 每层的最右边 O(N) O(N)
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        if(root == null) return result;
        Deque<TreeNode> currentLevel = new LinkedList<TreeNode>(){{add(root);}};
        while(!currentLevel.isEmpty()) {
            Deque<TreeNode> nextLevel = new LinkedList<>();
            result.add(currentLevel.getLast().val);
            while(!currentLevel.isEmpty()) {
                TreeNode node = currentLevel.removeFirst();
                if(node.left !=null) nextLevel.add(node.left);
                if(node.right !=null) nextLevel.add(node.right);
            }
            currentLevel = nextLevel;
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        System.out.println(new 遍历Level().rightSideView(root));
    }
}
