package u6.MultiThread.s0.synch.two_monitor;

public class Resource {
    private long c1 = 0;
    private long c2 = 0;

    private Object monitor1 = new Object();
    private Object monitor2 = new Object();

    public long getc1() {
        return c1;
    }

    public long getc2() {
        return c2;
    }

    //    public synchronized void inc1() {
    public void inc1() {
        synchronized (monitor1) {
            for (int i = 0; i < 5; ++i) {
                System.out.println(Thread.currentThread().getName());
                c1++;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }

    //    public synchronized void inc2() {
    public void inc2() {
        synchronized (monitor2) {
            for (int i = 0; i < 5; ++i) {
                System.out.println(Thread.currentThread().getName());
                c2++;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }

}
