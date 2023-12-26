package u6.multi_thread.s1.s7.consumer_producer_reentrant_read_write_lock_ticktock;

import java.util.concurrent.locks.*;

public class MainApp {
    public static Lock first = new ReentrantLock();
    public static Lock second = new ReentrantLock();

    public static void main(String[] args) {
        first.lock();
        second.lock();

        TickTock tt = new TickTock();

        new Thread(() -> {
            for (int i = 0; i < 10; ++i) {
                tt.tick();
            }
        }).start();
    }
}
