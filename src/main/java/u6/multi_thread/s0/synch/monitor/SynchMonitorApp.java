package u6.multi_thread.s0.synch.monitor;

public class SynchMonitorApp {
    private Object monitor = new Object();

    public void method(int number) {

        for (int i = 0; i < 3; i++) {
            System.out.println(number);
            try {
                Thread.sleep(400);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }

        synchronized (monitor) {
                /*
                for locks theme:
                If some thread works on the synchronized section -
                all other threads wait. We cannot ask "Is it unblocked?"
                before hitting in block - we can only hitting in block
                and start waiting. We cannot go away and do something
                else useful.
                 */
            for (int a = 0; a < 5; a++) {
                System.out.print(number);
                try {
                    Thread.sleep(400);
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
            }
            System.out.println();
        }

    }

    public static void main(String[] args) {
        SynchMonitorApp synchMonitorApp = new SynchMonitorApp();

        new Thread(() -> synchMonitorApp.method(1)).start();
        new Thread(() -> synchMonitorApp.method(2)).start();
        new Thread(() -> synchMonitorApp.method(3)).start();
    }
}
