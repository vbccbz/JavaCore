package u6.multi_thread.s4.s0.count_down_latch_instead_join;

import java.util.concurrent.*;

public class CountDownLatchApp {
    public static void main(String[] args) {
        final int THREADS_COUNT = 6;// if threads are scattered at all application and main() wants to wait for ending of all threads.
        // there isn't references so join() can't work.
        // create a latch and set how many times it must click.

        final CountDownLatch countDownLatch = new CountDownLatch(THREADS_COUNT);

        System.out.println("Начинаем");
        for (int i = 0; i < THREADS_COUNT; i++) {
            final int w = i;
            new Thread(() -> {
                try {
                    Thread.sleep(200 * w + (int) (500 * Math.random()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Поток #" + w + " - готов");
                countDownLatch.countDown();
            }).start();
        }

        try {
            countDownLatch.await();// thread wait() until the latch is dropped at 0.
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Работа завершена");
    }
}
