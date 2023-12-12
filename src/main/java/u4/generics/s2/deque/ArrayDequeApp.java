package u4.generics.s2.deque;

import java.util.ArrayDeque;
import java.util.Deque;

public class ArrayDequeApp {

    public static void main(String[] args) {
        // Stack is depricated.
        // LinkedList has the overhead of node allocations.
        // ArrayList has the overhead of shifting the array contents left on remove.
        Deque<Integer> deque = new ArrayDeque<>();
        deque.addLast(33);
        deque.addLast(22);
        deque.addLast(11);
        System.out.println(deque);
        while(!deque.isEmpty()){
            System.out.println(deque.pollLast());
        }

    }
}
