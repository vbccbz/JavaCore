package u6.multi_thread.s1.s1.consumer_producer_ticktock;

public class MyRunnablePlusThread implements Runnable {
    Thread thrd;
    TickTock ttOb;

    MyRunnablePlusThread(String name, TickTock tt) {
        thrd = new Thread(this, name);
        ttOb = tt;
    }

    public static MyRunnablePlusThread createAndStart(String name, TickTock tt) {
        MyRunnablePlusThread myThrd = new MyRunnablePlusThread(name, tt);
        myThrd.thrd.start();
        return myThrd;
    }

    @Override
    public void run() {
        // this : MyThread obj1 or MyThread obj2

        if (thrd.getName().compareTo("Tick") == 0) {
            for (int i = 0; i < 100; ++i) {
                ttOb.tick(true);
            }
            ttOb.tick(false);
        } else {// "Tock"
            for (int i = 0; i < 100; ++i) {
                ttOb.tock(true);
            }
            ttOb.tock(false);
        }
    }
}
