package interview.wang.排序检索;


/**
 * Floyd’s cycle finding algorithm or Hare-Tortoise algorithm is a pointer algorithm that uses only two pointers, moving through the sequence at different speeds.
 * 适用场合：探测（detect）链表中的环(loop)
 *
 *
 * */

public class 双指针Floyd {
    boolean detectLoop(Node head) {
        Node slowPointer = head, fastPointer = head;
        //移动
        while (slowPointer != null && fastPointer != null && fastPointer.next != null) {
            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next.next;
            if (slowPointer == fastPointer)
                return true;
        }
        return false;
    }

    //Test
    public static void main(String[] args) {
        Node head = new Node(10);
        head.next = new Node(2);
        head.next.next = new Node(4);
        head.next.next.next = new Node(9);

        Node temp = head;
        while (temp.next != null)
            temp = temp.next;
        //组成环
        temp.next = head;

        //测试
        if (new 双指针Floyd().detectLoop(head))
            System.out.print("Loop exists in the Linked List" +"\n");
        else
            System.out.print("Loop does not exists in the Linked List" +"\n");
    }
}

class Node {
    int data;
    Node next;

    Node(int data)
    {
        this.data = data;
        next = null;
    }
};

