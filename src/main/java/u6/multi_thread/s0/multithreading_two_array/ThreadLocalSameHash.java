package u6.multi_thread.s0.multithreading_two_array;

public class ThreadLocalSameHash {
    // static ThreadLocal<Integer> variable = new ThreadLocal<>();
    static ThreadLocal<Integer> variable = ThreadLocal.withInitial(() -> 1);
/*
main 2 7a79be86
Thread-0 1 7a79be86
Thread-0 3 7a79be86
main 2 7a79be86
 */

    public static void main(String[] args) {
        variable.set(2);
        System.out.println(Thread.currentThread().getName() + " " + variable.get() + " " + Integer.toHexString(variable.hashCode()));
        Thread th1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " " + variable.get() + " " + Integer.toHexString(variable.hashCode()));
            variable.set(3);
            System.out.println(Thread.currentThread().getName() + " " + variable.get() + " " + Integer.toHexString(variable.hashCode()));
        });
        th1.start();
        try {
            th1.join();
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " " + variable.get() + " " + Integer.toHexString(variable.hashCode()));

    }

}
