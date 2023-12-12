package u6.multi_thread.s1.s5.consumer_producer_ReentrantLock_tryLock;

import java.util.concurrent.locks.*;

public class TryLockApp {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();

        new Thread(() -> {
            if (lock.tryLock()) {
                try {
                    for (int i = 0; i < 5; ++i) {
                        System.out.println("A");
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException exception) {
                            exception.printStackTrace();
                        }
                    }
                } finally {
                    lock.unlock();
                }
            } else {
                System.out.println("A goes away...");
            }
        }).start();

        new Thread(() -> {
//            try {
//                if(lock.tryLock(500, TimeUnit.MILLISECONDS)){
            if (lock.tryLock()) {
                try {
                    for (int i = 0; i < 5; ++i) {
                        System.out.println("B");
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException exception) {
                            exception.printStackTrace();
                        }
                    }
                } finally {
                    lock.unlock();
                }
            } else {
                System.out.println("B goes away...");
            }
//            } catch (InterruptedException exception) {
//                exception.printStackTrace();
//            }
        }).start();

    }
}
