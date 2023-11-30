package u6.MultiThread.s0.synch.monitor;

public class MyRunnable implements java.lang.Runnable {
    String data;
    MyRunnable(String data){
        this.data = data;
    }

    @Override
    public void run() {
        synchronized (this) {
            for (int i = 0; i < 10; ++i){
                System.out.println(data);
            }
        }
    }

//    @Override
//    synchronized public void run() { // the same as first and doesn't violate overriding rules.
//
//    }


}
