package u6.MultiThread.s2.s0.exceptions_thread_interrupt;

public class ThreadIsInterruptedApp {
    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            boolean inter = false;
            while (true) {
//                if (Thread.interrupted()) {
//                if (Thread.currentThread().isInterrupted()) {
                if (inter) {
                    System.out.println("while has been interrupted");
                    break;
                }
                System.out.print("tick ");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    inter = true;
                }
            }
        });
        t.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t.interrupt();// The interrupt() works in the sleep() and in the wait() so it doesn't imply something like a stop().

        System.out.println("main end");
    }
}
