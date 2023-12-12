package u6.multi_thread.s0.synch.static_object_monitor_immutable;

public class MonitorStatic {
    private static Integer monitor = 0;
    /*
    * Here are 3 monitor. Integer is the immutable. If you've created Integer - you can't change it.
    * The value inside Integer is final. It's the same as with String. Have changed -> have created new object.
    * After first monitor++; the monitor in MonitorStatic will be another object.
    */
    public static void main(String[] args) {
        new Thread(()->{
            synchronized (monitor){
                System.out.println("1");
                monitor++;// monitor = monitor + 1;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
                System.out.println("!1");
            }
        }).start();
        new Thread(()->{
            synchronized (monitor){
                System.out.println("2");
                monitor++;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
                System.out.println("!2");
            }
        }).start();
        new Thread(()->{
            synchronized (monitor){
                System.out.println("3");
                monitor++;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
                System.out.println("!3");
            }
        }).start();
    }
}
