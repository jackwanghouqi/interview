package interview.core.collections;

import java.util.Comparator;
import java.util.PriorityQueue;

public class TestPriorityQueue {
    private int priority;
    public TestPriorityQueue(int priority) {
        this.priority = priority;
    }
    public static void main(String[] args) {
        PriorityQueue<TestPriorityQueue> p = new PriorityQueue<>(5, new Comparator<TestPriorityQueue>(){
            @Override
            public int compare(TestPriorityQueue arg0, TestPriorityQueue arg1) {
                if(arg0 == null || arg1 == null) {
                    return 0;
                }
                System.out.println(arg0.priority+":"+arg1.priority);
                int result = arg1.priority - arg0.priority;
                System.out.println(result);
                return result;
            }});
        p.add(new TestPriorityQueue(1));
        p.add(new TestPriorityQueue(1));
        p.add(new TestPriorityQueue(2));
        p.add(new TestPriorityQueue(3));
        System.out.println(p.poll());
        System.out.println(p.poll());
    }


    @Override
    public String toString() {
        return "TestPriorityQueue{" +
                "priority=" + priority +
                '}';
    }
}
