package u6.multi_thread.s5.s3.executor_sevice_runnable_submit_BAD;

import java.util.concurrent.*;

public class submitApp {
    public static void main(String[] args) {
        System.out.println(" main start");

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Future future1 = executorService.submit(new Runnable() {// returns "executed" or "not executed"
            @Override
            public void run() {
                for (int i = 0; i < 10; ++i) {
                    System.out.println("A");
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException exception) {
                        exception.printStackTrace();
                    }
                }
                throw new  RuntimeException();
            }
        });

        try {
            System.out.println("???");

            Object wtf = future1.get();// blocking function, returns null if OK
            if (wtf != null){
                System.out.println("non null");
            }else{
                System.out.println("UNULL");
            }
//            System.out.println(future1.get());// blocking function, returns null if OK

//            try {
//                System.out.println(future1.get(10, TimeUnit.MILLISECONDS));// blocking function, returns null if OK
//            } catch (TimeoutException e) {
//                System.out.println("exception");
//                e.printStackTrace();
//            }

            System.out.println("???");

        } catch (InterruptedException exception) {
            exception.printStackTrace();
        } catch (ExecutionException executionException) {
            executionException.printStackTrace();
        }

        executorService.shutdown();

        System.out.println(" main end");
    }
}
