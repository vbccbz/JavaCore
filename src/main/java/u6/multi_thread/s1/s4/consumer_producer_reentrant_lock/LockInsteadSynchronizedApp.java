package u6.multi_thread.s1.s4.consumer_producer_reentrant_lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockInsteadSynchronizedApp {
    public static void main(String[] args) {
        final Lock lock = new ReentrantLock();

        new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();// blocking function
                try {
                    // lock.lockInterruptibly(); can throw InterruptedException
                    for (int i = 0; i < 5; ++i) {
                        System.out.println("A");
                        Thread.sleep(10);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // any exception can ba thrown (arithmetic, etc)
                    // finally guaranties unlock()
                    lock.unlock();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    for (int i = 0; i < 5; ++i) {
                        System.out.println("B");
                        Thread.sleep(100);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }).start();
    }
}
