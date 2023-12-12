package u6.multi_thread.s0.synch.static_shared_resource;

public class MyRunnable implements Runnable {
    Thread thrd;
    int[] data;
    int sum;
    static Resource staticSA = new Resource();

    MyRunnable(String name, int[] data) {
        thrd = new Thread(this, name);
        this.data = data;
    }

    public static MyRunnable createAndStart(String name, int[] data) {
        MyRunnable myThrd = new MyRunnable(name, data);
        myThrd.thrd.start();
        return myThrd;
    }

    @Override
    public void run() {
        System.out.println(thrd.getName() + " run ");
        synchronized (staticSA) {
            sum = staticSA.sumArray(data);
        }
    }
}
