package interview.wang.排序检索;

import java.util.Collections;
import java.util.List;

public class 链表反转Reverse {
    //【1】Java 集合类
    public void reverseI(List list) {
        Collections.sort(list, Collections.reverseOrder());
    }

    //【2】Iterative
    public void reverseII(Node head) {
        Node previous = null;
        Node current = head;
        while(current != null) {
            Node next = current.next;
            current = previous;
            previous = current;
            current = next;
        }
        head = previous;
    }

    //【2】Recursively
    public static Node recursiveReverse(Node head) {
        Node first;
        if (head==null || head.next == null)
            return head;
        first = recursiveReverse(head.next);
        //next的链接 指向 当前
        head.next.next = head;
        //擦除 当前的链接
        head.next = null;
        return first;
    }

}