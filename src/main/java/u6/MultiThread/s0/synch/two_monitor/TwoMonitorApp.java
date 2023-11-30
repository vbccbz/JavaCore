package u6.MultiThread.s0.synch.two_monitor;

public class TwoMonitorApp {
    public static void main(String[] args) {
        Resource resource = new Resource();

        Thread t1 = new Thread(() -> {
            resource.inc1();
        });

        Thread t2 = new Thread(resource::inc2);// funny

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }

        System.out.println("1: " + resource.getc1());
        System.out.println("2: " + resource.getc2());

    }
}
