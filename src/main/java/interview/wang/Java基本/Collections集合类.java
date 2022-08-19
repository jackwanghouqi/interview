package interview.wang.Java基本;

import java.util.*;
import java.util.concurrent.*;

public class Collections集合类 {
    public void sort(List<String> list) {
        Collections.sort(list); //case sensitive
        Collections.sort(list, String.CASE_INSENSITIVE_ORDER);
    }

    //【FILO】先进后出
    public void stack(Stack stack) {
        stack.push("1");
        Object peek = stack.peek(); // O(1)
        Object pop = stack.pop(); // O(1)
        stack.search("1"); // O(n)
        stack.empty(); //check size
        stack.size();
    }

    /**【队列】【FIFO】先进先出
     *
     * 【功能分类】 [普通][双端][优先][延迟][其他]
     * 【大小分类】 [有界bounded][无界]
     * 【阻塞分类】 [阻塞Blocking][非阻塞]
     *
     * 比较 concurrent access scenarios   https://www.baeldung.com/java-queue-linkedblocking-concurrentlinked
     *  【线程安全】【无界】 ConcurrentLinkedQueue
     *  【线程安全】【optionally有界】 LinkedBlockingQueue
     */
    public void queue(Queue queue) {
        boolean add = queue.add("1"); //throw exception on failure (for all Collection interface)
        boolean offer = queue.offer("2"); // 避免Exception

        Object element = queue.element(); //Queue don'e accept null, so if no element throw NoSuchElementException
        Object peek = queue.peek(); //can be null
        Object poll = queue.poll(); //retrieve && remove
        Object remove = queue.remove();
        queue.isEmpty(); //check size
        queue.size();
    }

    //【双端队列】combination of queue & stack
    public void deque(Deque deque) {
        queue(deque);// Deque is a Queue
        deque.addFirst("1"); //throw exception on failure (for all Collection interface)
        deque.addLast("1");
        boolean offerFirst = deque.offerFirst("2");  // 避免Exception
        boolean offerLast = deque.offerLast("2");
        boolean contains = deque.contains("1");
        deque.element();
        deque.peekFirst();
        deque.peekLast();
        deque.pollFirst();
        deque.pollLast();
        deque.removeFirst();
        deque.removeLast();
        deque.removeFirstOccurrence("1");
        deque.removeLastOccurrence("2");
        Iterator iterator = deque.iterator();
        Iterator descendingIterator = deque.descendingIterator();
    }

    //【优先队列】
    public void priorityQueue(PriorityQueue<String> priorityQueue) {
        Spliterator<String> spliterator = priorityQueue.spliterator();
        spliterator.forEachRemaining((n) -> System.out.println(n));
        //Comparator
        PriorityQueue<String> newPriorityQueue = new PriorityQueue<String>((String u1, String u2)->{ if (u1.charAt(0) == 'V') return -1; else if (u2.charAt(0) == 'V') return -1; else return u1.compareTo(u2);});
    }

    //【阻塞队列】 Array Linked Priority  --- BlockingQueue
    public void blockingQueue(BlockingQueue<String> blockingQueue) throws InterruptedException {
        int capacity = 5;
        ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<String>(capacity);
        blockingQueue.drainTo(arrayBlockingQueue);

        blockingQueue.offer("1");
        blockingQueue.offer("2", 20, TimeUnit.HOURS);
        blockingQueue.poll();
        blockingQueue.poll(2, TimeUnit.MINUTES);
        blockingQueue.remainingCapacity();
    }

    //【延迟队列】 到达指定的【延迟时间】之后方可出队
    public void DelayQueue(DelayQueue delayQueue) throws InterruptedException {
        delayQueue.put(new MyDelay(1000, "消息1"));
        delayQueue.put(new MyDelay(3000, "消息2"));
        while (!delayQueue.isEmpty()) {
            System.out.println(delayQueue.take());
        }
    }

    //【特殊队列-无集合容器】 只存一个
    public void synchronousQueue(SynchronousQueue synchronousQueue) {
        synchronousQueue.offer("1");
        synchronousQueue.poll();
    }

    static class MyDelay implements Delayed {
        long delayTime = System.currentTimeMillis();
        String msg;
        public MyDelay(long delayTime, String msg) {
            this.delayTime = (this.delayTime + delayTime);
            this.msg = msg;
        }
        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(delayTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            if (this.getDelay(TimeUnit.MILLISECONDS) > o.getDelay(TimeUnit.MILLISECONDS)) {
                return 1;
            } else if (this.getDelay(TimeUnit.MILLISECONDS) < o.getDelay(TimeUnit.MILLISECONDS)) {
                return -1;
            } else {
                return 0;
            }
        }
    }


    public static void main(String[] args) {
        String[] arr = new String[] { "P", "W", "g", "K", "H", "t", "E" };
        List<String> list = Arrays.asList(arr);
        Collections.sort(list); //case sensitive
        System.out.println(list);
        //Case Insensitive Sort

    }
}

/**
 * https://www.cnblogs.com/vipstone/p/13862311.html
 * 理解队列三大分类，理解线程安全常用的queue
 *
 * */