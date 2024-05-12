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
                System.out.println("create thread " + thread.getName());
                return thread;
            }
        });
        executorService.execute(()->{
            System.out.println("1" + " " + Thread.currentThread().getName());
        });
        executorService.execute(()->{
            System.out.println( "2" + " " + Thread.currentThread().getName());
        });
        executorService.execute(()->{
            System.out.println( "3" + " " + Thread.currentThread().getName());
        });
        executorService.execute(()->{
            System.out.println("4" + " " + Thread.currentThread().getName());
        });
        executorService.execute(()->{
            System.out.println("5" + " " + Thread.currentThread().getName());
        });
        executorService.shutdown();

    }
}
