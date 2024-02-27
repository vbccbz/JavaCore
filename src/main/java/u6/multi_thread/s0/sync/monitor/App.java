package u6.multi_thread.s0.sync.monitor;

public class App {
    public static void main(String[] args) {
        java.lang.Thread thread1 = new java.lang.Thread(new MyRunnable("A"));
        java.lang.Thread thread2 = new java.lang.Thread(new MyRunnable("B"));

        thread1.start();
        thread2.start();

    }
}
