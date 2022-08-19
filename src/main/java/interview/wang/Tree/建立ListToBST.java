package interview.wang.Tree;

import java.util.ArrayList;
import java.util.List;

/**
 *   【Sorted Link List To Binary Tree】
 * Solution1: 递归  时间复杂度 O(NlogN)   空间复杂度 O(logN)  递归栈占用的空间
 *
 * Solution2: 转换成Array or ArrayList 时间复杂度 O(N)   空间复杂度 O(N) 忽略递归栈O(logN)
 * */
public class 建立ListToBST {
    //【Solution1】
    public TreeNode sortedListToBST(ListNode head) {
        if(head == null) return null;
        ListNode mid  = middle(head);
        TreeNode parent  = new TreeNode(mid.val);
        if(head.val == parent.val) {
            return parent;
        }
        parent.left = sortedListToBST(head);
        parent.right = sortedListToBST(mid.next);
        return parent;
    }
    private ListNode middle(ListNode head) {
        ListNode prev = null;
        ListNode slowRef = head;
        ListNode fasterRef = head;

        //Iterate 2 ref
        while(fasterRef != null && fasterRef.next != null) {
            prev = slowRef;
            slowRef = slowRef.next;
            fasterRef = fasterRef.next.next;
        }

        //split the link
        if(prev != null) {
            prev.next = null;
        }
        return slowRef;
    }

    //【Solution2】
    public TreeNode sortedListToBSTII(ListNode head) {
        List<Integer> values= new ArrayList<Integer>();
        while (head != null) {
            values.add(head.val);
            head = head.next;
        }
        // 【Convert the array】
        return toBST(values, 0, values.size() - 1);
    }

    private TreeNode toBST(List<Integer> values, int left, int right) {
        if (left > right) return null;

        // 【Middle element】
        int mid = (left + right) / 2;
        TreeNode node = new TreeNode(values.get(mid));

        // 只有一个element
        if (left == right) {
            return node;
        }

        // 【Recursively form BST on the two halves】
        node.left = toBST(values, left, mid - 1);
        node.right = toBST(values, mid + 1, right);
        return node;
    }
}

class ListNode { int val; ListNode next; ListNode() {} ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; } }
