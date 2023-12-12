package u6.multi_thread.s2.s3.exceptions_javarush;


public class App {

    static class TestedThread extends Thread {
        public void run() {
            // System.out.println(Thread.currentThread().getName()); // is ok
            System.out.println(getName());// Why is there difference?
        }
    }

    public static void main(String[] args) {
        TestedThread base = new TestedThread();

        Thread derived = new Thread(base);
        derived.setName("derived");

        base.start();
        derived.start();
    }
}
