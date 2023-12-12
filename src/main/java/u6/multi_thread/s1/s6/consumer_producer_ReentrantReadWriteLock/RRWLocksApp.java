package u6.multi_thread.s1.s6.consumer_producer_ReentrantReadWriteLock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class RRWLocksApp {
    public static void main(String[] args) {
        ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
        Resource r = new Resource();

        new Thread(() -> {
            for (int i = 0; i < 5; ++i) {
                try {
                    rwl.writeLock().lock();
                    System.out.println(i + " WRITING start ");
                    r.put(i);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(i + " WRITING end ");
                } finally {
                    rwl.writeLock().unlock();
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 5; ++i) {
                try {
                    rwl.readLock().lock();
                    System.out.println(i + "\t\t READING A start ");
                    r.put(i);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(i + "\t\t READING A end " );
                } finally {
                    rwl.readLock().unlock();
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 5; ++i) {
                try {
                    rwl.readLock().lock();
                    System.out.println(i + "\t\t\t\t READING B start ");
                    r.put(i);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(i + "\t\t\t\t READING B end " );
                } finally {
                    rwl.readLock().unlock();
                }
            }
        }).start();



    }
}
