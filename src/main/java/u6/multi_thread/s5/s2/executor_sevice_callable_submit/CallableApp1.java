package u6.multi_thread.s5.s2.executor_sevice_callable_submit;

import java.lang.*;
import java.util.concurrent.*;

public class CallableApp1 {
    public static void main(String[] args) {
        System.out.println(" main start ");

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Future<String> stringFutureA = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("A");

                Thread.sleep(2000);
                /*
                прикол, что здесь не требуется try!!!
                Из обычного треда исключения не попадают в main. Но колабл необычный тред.
                Если экспешн вылетает в колабл (например, деление на ноль), то эксепшн будет прокинут в поток, который выполняет get().
                Если get() отсутствуте - экспешн вообще теряется.
                */

                int x = 1;
                x = x / 0;
                // магия!
                // смотри следующий пример
                return "RETURNED";
            }
        });


        executorService.shutdown();// non blocking ?
        System.out.println(" main end ");
    }
}
