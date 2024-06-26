package u6.multi_thread.s7.volatileVariable;

public class WaitNotifyApp {
    private final Object mon = new Object();
    private volatile char nextLetter = 'A';
    // The 1 thread see multiple set operations and caches the nextLetter in the cash memory of the 1 core.
    // But the 2 thread on the 2 core gets data from a general memory.
    // The volatile denies the caching.
    // But in this example volatile isn't needed here due to synchronized.

    public static void main(String[] args) {
        WaitNotifyApp waitNotifyApp = new WaitNotifyApp();
        new Thread(() -> {
            waitNotifyApp.printA();
        }).start();
        new Thread(() -> {
            waitNotifyApp.printB();
        }).start();
    }

    public void printA() {// and prepare B
        synchronized (mon) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (nextLetter != 'A') {
                        mon.wait();
                    }
                    System.out.print("A");
                    nextLetter = 'B';
                    mon.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void printB() {// and prepare A
        synchronized (mon) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (nextLetter != 'B') {
                        mon.wait();
                    }
                    System.out.print("B");
                    nextLetter = 'A';
                    mon.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
