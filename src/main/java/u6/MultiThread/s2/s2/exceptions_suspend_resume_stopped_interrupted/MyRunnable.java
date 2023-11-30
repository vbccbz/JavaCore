package u6.MultiThread.s2.s2.exceptions_suspend_resume_stopped_interrupted;

public class MyRunnable implements Runnable {
    Thread thrd;
    boolean suspend;
    boolean stopped;
    boolean interrupted;

    public MyRunnable() {
        thrd = new Thread(this);
        suspend = false;
        stopped = false;
        interrupted = false;
    }

    public static MyRunnable createAndRun() {
        MyRunnable mt = new MyRunnable();
        mt.thrd.start();
        return mt;
    }

    @Override
    public void run() {
        while (true) {
//            if (stopped || interrupted || Thread.currentThread().isInterrupted()) {
            //Thread.interrupted() is strange
            if (stopped) {
                break;
            }
            while (suspend) {
                synchronized (this) {
                    try {
                        wait();
                    } catch (InterruptedException exception) {
                        suspend = false;//  doubtful, coz I have a resume() and a stop().
                    }
                }
            }

            System.out.print("work");
            try {
                Thread.sleep(100);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
                System.out.println("I'm not sleeping, but doing something right now and I don't care about your interrupt()");
            }
        }
    }

    synchronized public void suspend() {
        System.out.print("suspend:" + Thread.currentThread().getName() + " ");
        suspend = true;
    }

    synchronized public void resume() {
        System.out.print("resume:" + Thread.currentThread().getName() + " ");
        suspend = false;
        notify();
    }

    synchronized public void stop() {
        System.out.print("stop:" + Thread.currentThread().getName() + " ");
        suspend = false;
        stopped = true;
        notify();
    }
}
