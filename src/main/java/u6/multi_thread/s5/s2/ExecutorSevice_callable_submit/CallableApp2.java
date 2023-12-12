package u6.multi_thread.s5.s2.ExecutorSevice_callable_submit;

import java.lang.*;
import java.util.concurrent.*;

public class CallableApp2 {
    public static void main(String[] args) {
        System.out.println(" main start ");

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Future<String> stringFuture = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                int x = 1 / 0;
                return "RETURNED";
            }
        });

        try {
            System.out.println("111");
            String result = stringFuture.get();// blocking function
            System.out.println(result);// will be jumped over...
            System.out.println("222");
        } catch (InterruptedException e) {
            // due to Callable logic - we need catch it here.
//            e.printStackTrace();
            // It simply doesn't catch in this example
        } catch (ExecutionException e) {
            System.out.println("You are here " + Thread.currentThread().getName());
            // chained exception
            e.printStackTrace();//
            // prints two traces!
        }

        executorService.shutdown();// non blocking ?
        System.out.println(" main end ");
    }
}
