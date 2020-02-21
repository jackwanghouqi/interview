package interview.core.collections;

import java.util.HashSet;
import java.util.Stack;
import java.util.TreeSet;
import java.util.Vector;
import java.util.concurrent.DelayQueue;

public class TestVector {
    public static void main(String[] args) {
        Vector vector = new Vector();
        vector.add(null);
        vector.insertElementAt(new Object(), 1);
        Stack stack = new Stack();
        stack.add(null);
        //below is wrong in term of stack
        stack.insertElementAt(new Object(), 1);


        //DelayQueue
    }
}
