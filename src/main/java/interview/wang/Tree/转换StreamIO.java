package interview.wang.Tree;

import java.util.*;

public class 转换StreamIO {
    public String serialize(TreeNode root) {
        if(root == null) return "N,";
        String result = root.val+",";
        result += serialize(root.left);
        result += serialize(root.right);
        return result;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] dataSplits = data.split(",");
        Stack<String> dataStack = new Stack<>();
        for (int i = dataSplits.length-1; i >=0 ; i--) {
            dataStack.push(dataSplits[i]);
        }
        return _deserialize(dataStack);
    }

    private TreeNode _deserialize(Stack<String> dataStack) {
        if(dataStack.isEmpty()) return null;
        String str = dataStack.pop();
        if("N".equals(str)) return null;
        TreeNode node = new TreeNode(Integer.parseInt(str));
        node.left = _deserialize(dataStack);
        node.right = _deserialize(dataStack);
        return node;
    }

    public static void main(String[] args) {
        TreeNode node = new 转换StreamIO().deserialize("1,2,3,N,N,4,5");
        System.out.println(node);
        System.out.println(new 转换StreamIO().serialize(node));
    }
}
