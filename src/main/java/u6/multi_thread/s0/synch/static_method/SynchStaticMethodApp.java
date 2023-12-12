package u6.multi_thread.s0.synch.static_method;

public class SynchStaticMethodApp {// классы в джаве загружаются в мета-спэйс
    public static void main(String[] args) {
        SynchStaticMethodApp e = new SynchStaticMethodApp();
        new Thread(() -> e.objectMethod()).start();
        new Thread(() -> classMethod()).start();
        // both use 2 different monitors
    }

    public synchronized static void classMethod() {// The class acts as monitor.
        System.out.println("Synch Static Method Start");
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Synch Static Method End");
    }

    public synchronized void objectMethod() {// The objects acts as monitor.
        System.out.println("Synch Method Start");
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Synch Method End");
    }
}
