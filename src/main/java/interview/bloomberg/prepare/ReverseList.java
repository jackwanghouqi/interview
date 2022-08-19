package interview.bloomberg.prepare;

public class ReverseList {
    public ListNode reverseList(ListNode head) {
        ListNode nodePrevious = null;

        while (head!= null ) {
            ListNode nodeTemp = head.next;
            head.next = nodePrevious;
            nodePrevious = head;
            head = nodeTemp;
        }
        return nodePrevious;
    }

    public static void main(String[] args) {
        ListNode testObj = new ListNode();
        testObj.next = new ListNode();
        ReverseList test = new ReverseList();
        test.reverseList(testObj);
    }
}
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
