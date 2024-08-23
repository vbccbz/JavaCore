package u6.multi_thread.s0.sync.monitor;

public class App {
    public static void main(String[] args) {
        String first = "a";
        String second = "b";
        java.lang.Thread thread1 = new java.lang.Thread(new MyRunnable(first));
        java.lang.Thread thread2 = new java.lang.Thread(new MyRunnable(first));

        thread1.start();
        thread2.start();

    }
}
