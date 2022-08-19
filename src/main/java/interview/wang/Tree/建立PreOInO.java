package interview.wang.Tree;

import java.util.HashMap;
import java.util.Map;

/*
*
* */
public class 建立PreOInO {
    int preIndex;
    Map<Integer, Integer> inorderIndexMap;
    /*★★【由Preorder确定root,由Inorder确定left】  【使用 HashMap】O(N)  O(logN)~O(N)*/
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        preIndex = 0;
        inorderIndexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderIndexMap.put(inorder[i], i);
        }
        return buildTree(preorder,inorder, 0,inorder.length-1);
    }

    public TreeNode buildTree(int[] preorder, int[] inorder, int left, int right) {
        if(left > right || preIndex > preorder.length-1) return null;
        TreeNode root = new TreeNode(preorder[preIndex]);
        int mid = getIndex(inorder, preorder[preIndex], left, right); //【HashMap】 ==> return inorderIndexMap.get(value);
        preIndex++;
        root.left = buildTree(preorder, inorder, left,mid-1);
        root.right = buildTree(preorder, inorder, mid+1,right);
        return root;
    }

    //不用 HashMap O(N*logN)
    private int getIndex(int[] inorder, int value, int from, int to) {
        for(int i=from; i<= to; i++) if(value == inorder[i]) return i;
         return -1;
    }
}
/*https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/*/