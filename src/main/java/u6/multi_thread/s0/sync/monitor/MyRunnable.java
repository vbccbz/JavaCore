package u6.multi_thread.s0.sync.monitor;

public class MyRunnable implements java.lang.Runnable {
  String data;

  MyRunnable(String data) {
    this.data = data;
  }

  @Override
  public void run() {
    // synchronized (this) {
    synchronized (data) {
      for (int i = 0; i < 10; ++i) {
        System.out.println(Thread.currentThread().getName());
        try {
          Thread.sleep(10);
        } catch (InterruptedException exc) {
        }
      }
    }
  }

//    @Override
//    synchronized public void run() { // the same as first and doesn't violate overriding rules.
//
//    }


}
