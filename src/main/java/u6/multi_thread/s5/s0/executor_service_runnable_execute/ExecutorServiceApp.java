package u6.multi_thread.s5.s0.executor_service_runnable_execute;

import java.lang.*;
import java.util.concurrent.*;

public class ExecutorServiceApp {
    public static void main(String[] args) {
        System.out.println("start main");

        ExecutorService executorService = Executors.newSingleThreadExecutor();
//         big queue with 1 thread

//        ExecutorService executorService = Executors.newFixedThreadPool(2);
//         big queue with 2 thread

//        ExecutorService executorService = Executors.newCachedThreadPool();
//         big queue with many threads

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; ++i) {
                    System.out.println("A + " + Thread.currentThread().getName());
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException exception) {
                        exception.printStackTrace();
                    }
                }
            }
        });

        executorService.execute(() -> {
                    for (int i = 0; i < 10; ++i) {
                        System.out.println("B + " + Thread.currentThread().getName());
                        try {
                            Thread.sleep(300);
                        } catch (InterruptedException exception) {
                            exception.printStackTrace();
                        }
                    }
                }
        );

        System.out.println("shutdown executorSevice...");
        executorService.shutdown();// non-blocking

        System.out.println("wait");
        try {
            executorService.awaitTermination(1, TimeUnit.MILLISECONDS);
        } catch (
                InterruptedException exception) {
            exception.printStackTrace();
        }
        System.out.println("end main");

    }
}
