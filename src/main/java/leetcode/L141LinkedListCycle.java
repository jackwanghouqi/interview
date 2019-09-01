package leetcode;

/**
 * Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
 * Try solving it using constant additional space.
 * */
public class L141LinkedListCycle {
	
	public static void main(String[] args) {
		
		System.out.println(0%9);
		System.out.println(9%6);
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3);
		ListNode node4 = new ListNode(4);
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node3;
		ListNode beginningOfCycle = getBeginningOfCycleEnhanced(node1);
		System.out.println(beginningOfCycle);
		
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node1;
		beginningOfCycle = getBeginningOfCycleEnhanced(node1);
		System.out.println(beginningOfCycle);
		
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node2;
		beginningOfCycle = getBeginningOfCycleEnhanced(node1);
		System.out.println(beginningOfCycle);
		
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node4;
		beginningOfCycle = getBeginningOfCycleEnhanced(node1);
		System.out.println(beginningOfCycle);
	}
	
	
	/**
	 * 1. find out if it has Cycle
	 * 2. reuse the slower pointer to run in the cycle until meet faster point(not move any more, just for a mark in the circle) again.
	 * 3. check if the the begin point meets slower point again.
	 * 4. if within one cyclic run, slower didn't meet the begin point, move the begin point one step further and repeat step 2.
	 * 5. until slower meets the begin point. return the begin point.
	 * 
	 * Time complexity O(N+K+ (N*K)), which is O(N*K)
	 * Space complexity : O(1)
	 * */
	public static ListNode getBeginningOfCycle(ListNode head) {
		if(head == null || head.next == null) {
			return null;
		}
		ListNode slow = head;
		ListNode fast = head.next;
		while(slow!=fast) {
			if(fast == null || fast.next == null) {
				return null;
			}
			slow = slow.next;
			fast = fast.next.next;
		}
		
		ListNode begin = head;
		while(begin != slow) {
			slow = slow.next;
			while(slow!=fast) {
				if(begin==slow) {
					return begin;
				}
				slow = slow.next;
			}
			begin = begin.next;
		}
		
		return begin;
	}
	
	public static ListNode getBeginningOfCycleEnhanced(ListNode head) {
		if(head == null || head.next == null) {
			return null;
		}
		ListNode slow = head;
		ListNode fast = head.next;
		while(slow!=fast) {
			if(fast == null || fast.next == null) {
				return null;
			}
			slow = slow.next;
			fast = fast.next.next;
		}
		
		while(slow.next != null) {
			ListNode node = slow;
			slow = slow.next;
			node.next = null;
		}
		
		ListNode begin = head;
		while(begin.next != null) {
			begin = begin.next;
		}
		return begin;
	}
	
	/*
	 * leetcode just check if there is cycle. 
	 * while we are going to find the beginning of the cycle.
	 * So check with another function
	 * 
	 * Time complexity O(N+K), which is O(n)
	 * non-cyclic length=N
	 * Number of iterations=almost "cyclic length K"
	 * 
	 * Space complexity : O(1)
	 * 
	 * */
	
	public static boolean hasCycle(ListNode head) {
		if(head == null || head.next == null) {
			return false;
		}
		ListNode slow = head;
		ListNode fast = head.next;
		while(slow!=fast) {
			if(head == null || head.next == null) {
				return false;
			}
			slow = slow.next;
			fast = fast.next.next;
		}
		return true;
	}
	
	public static class ListNode {
		int id;
		public ListNode(int id) {
			super();
			this.id = id;
		}
		ListNode next;
		@Override
		public String toString() {
			return "ListNode [id=" + id + "]";
		}
	}
}
