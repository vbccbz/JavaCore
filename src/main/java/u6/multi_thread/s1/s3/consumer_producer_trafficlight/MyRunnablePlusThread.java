package u6.multi_thread.s1.s3.consumer_producer_trafficlight;

public class MyRunnablePlusThread implements Runnable {
    public enum TrafficLightColor {
        RED,
        GREEN,
        YELLOW
    }

    private Thread thrd;
    private TrafficLightColor tlc;
    private boolean stopped;
    private boolean changed;

    public MyRunnablePlusThread(TrafficLightColor c) {
        tlc = c;
        thrd = new Thread(this);
        stopped = false;
        changed = false;
    }

    public static MyRunnablePlusThread createAndRun(TrafficLightColor c) {
        MyRunnablePlusThread tl = new MyRunnablePlusThread(c);
        tl.thrd.start();
        return tl;
    }

    @Override
    public void run() {
        try {
            while (!stopped) {
//                changed = false;
                switch (tlc) {
                    case GREEN:
                        Thread.sleep(3000);
                        break;
                    case RED:
                        Thread.sleep(5000);
                        break;
                    case YELLOW:
                        Thread.sleep(1000);
                        break;
                }
                changeColor();//
            }
        } catch (InterruptedException exc) {
            exc.printStackTrace();
        }
    }

    synchronized private void changeColor() {
        switch (tlc) {
            case GREEN:
                tlc = TrafficLightColor.YELLOW;
                break;
            case YELLOW:
                tlc = TrafficLightColor.RED;
                break;
            case RED:
                tlc = TrafficLightColor.GREEN;
                break;
            default:
                break;
        }
        changed = true;
        notify();
    }

    synchronized void stop() {
        stopped = true;
    }

    synchronized public TrafficLightColor getTlc() {
        return tlc;
    }

    synchronized public void waitUntilChanged() {
        System.out.println(Thread.currentThread().getName());
        while (!changed) {
            try {
                wait();
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }
        changed = false;// !
    }
}
