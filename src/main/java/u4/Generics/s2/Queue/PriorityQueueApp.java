package u4.Generics.s2.Queue;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class PriorityQueueApp {

    public static void main(String[] args) {
        Queue<Integer> pqueue = new PriorityQueue<>(5);
        pqueue.offer(5);
        pqueue.offer(10);
        pqueue.offer(3);
        System.out.println(pqueue);
        while(pqueue.size() != 0){
            System.out.println(pqueue.remove());
        }

    }
}
