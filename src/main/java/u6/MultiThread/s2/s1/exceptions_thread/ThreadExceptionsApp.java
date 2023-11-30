package u6.MultiThread.s2.s1.exceptions_thread;

public class ThreadExceptionsApp {
    public static void main(String[] args) {
        System.out.println("main start");

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t start");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
                throw new RuntimeException("Hello!");
            }
        });
        t.start();

        try {
            Thread.sleep(10);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }

        t.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.err.println(t.getName());// System.err.println(Thread.currentThread().getName());
                System.err.println(e);// java.lang.RuntimeException: + e.getMessage()
//                e.printStackTrace();

//                throw new RuntimeException("just for lulz");
            }
        });

//        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
//            // on all threads
//            @Override
//            public void uncaughtException(Thread t, Throwable e) {
//                 System.err.println(t.getName());// System.err.println(Thread.currentThread().getName());
//                System.err.println(e);// java.lang.RuntimeException: + e.getMessage()
////                e.printStackTrace();
//
////                throw new RuntimeException("just for lulz");
//            }
//        });

        try {
            t.join();
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        System.out.println("main end");
    }
}
