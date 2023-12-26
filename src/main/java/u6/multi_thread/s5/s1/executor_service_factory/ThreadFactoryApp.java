package u6.multi_thread.s5.s1.executor_service_factory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class ThreadFactoryApp {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                System.out.println("create thread... ");
                return thread;
            }
        });
        executorService.execute(()->{
            System.out.println(Thread.currentThread().getName());
            System.out.println("1");
        });
        executorService.execute(()->{
            System.out.println(Thread.currentThread().getName());
            System.out.println("2");
        });
        executorService.execute(()->{
            System.out.println(Thread.currentThread().getName());
            System.out.println("3");
        });
        executorService.execute(()->{
            System.out.println(Thread.currentThread().getName());
            System.out.println("4");
        });
        executorService.execute(()->{
            System.out.println(Thread.currentThread().getName());
            System.out.println("5");
        });
        executorService.shutdown();

    }
}
