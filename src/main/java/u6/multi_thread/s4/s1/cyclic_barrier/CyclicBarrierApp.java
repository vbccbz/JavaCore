package u6.multi_thread.s4.s1.cyclic_barrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierApp {
    public static void main(String[] args) {
        int THREADS = 2;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(THREADS);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("A...");

//                    Thread.sleep(500);

                    System.out.println("A wait");
                    cyclicBarrier.await();

                    System.out.println("A");

                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("B...");

                    Thread.sleep(2000);

                    cyclicBarrier.await();

                    System.out.println("B");

                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
