package u6.MultiThread.s7.priority;

public class PriorityApp {
    public static void main(String[] args) {
        MyThread h = new MyThread("H");
        MyThread l = new MyThread("L");
        MyThread mt4 = new MyThread("N4");
        MyThread mt5 = new MyThread("N5");
        MyThread mt6 = new MyThread("N6");

        h.thrd.setPriority(Thread.NORM_PRIORITY + 2);
        l.thrd.setPriority(Thread.NORM_PRIORITY - 2);

        h.thrd.start();
        l.thrd.start();
        mt4.thrd.start();
        mt5.thrd.start();
        mt6.thrd.start();

        try {
            h.thrd.join();
            l.thrd.join();
            mt4.thrd.join();
            mt5.thrd.join();
            mt6.thrd.join();
        } catch (InterruptedException exc) {
            System.out.println("interrupted main");
        }

        System.out.print("h.count " + h.count + " ");
        System.out.print("l.count " + l.count + " ");
        System.out.print("mt4.count " + mt4.count + " ");
        System.out.print("mt5.count " + mt5.count + " ");
        System.out.print("mt6.count " + mt6.count + " ");
    }
}
