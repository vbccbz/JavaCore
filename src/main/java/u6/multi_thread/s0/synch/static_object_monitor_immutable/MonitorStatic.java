package u6.multi_thread.s0.synch.static_object_monitor_immutable;

public class MonitorStatic {
    private static Integer monitor1 = 0;
    private static Integer monitor2 = 0;// the same object in memory as monitor :D so, do not do that

    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println("1 before synch");
            synchronized (monitor1) {
                System.out.println("1");
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
                monitor1++;
                /* Integer is the immutable. The value inside Integer is final. It is similar to String.
                If you've created Integer - you can't change it, but can replace.
                After ++, monitor1 will refere to another object.
                If Thread2 has time to connect to old monitor1 - he will be attached to old monitor1.
                If next threads will sync on monitor - they will infer with previous threads.
                */

                System.out.println("1 changed monitor");
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
                System.out.println("!1");
            }
        }).start();

        new Thread(() -> {
            System.out.println("2 before synch");
            synchronized (monitor1) {
                System.out.println("2");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
                System.out.println("!2");
            }
        }).start();
    }
}
