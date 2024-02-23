package u6.multi_thread.s0.multithreading_two_array;

import java.util.Arrays;

public class TwoArrayApp {
    public static void main(String[] args) {
/*
Process
    Files(???)
    Data(statics)
    Code(Text)
    Threads
        Registers
        Stack
        Heap(each thread has separate heap block)

True parallel can work only on at least 2 core.

Objects and classes have the Monitor. The Monitor is an abstraction for synchronizing. Synchronized doesn't mean "simultaneously executing". It means "synchronized access to code - only one thread can process this code.

At first, MyRunnable looks more logical than SomeClassName. But for real - many classes implements many interfaces. And their names isn't correspond with interface's names. The implementation of interface endows attributes to a type(class). Instead "MyRunnable" you can use "ArrayHandler".

_Thread_local int tmp; does copy of static block in thread.
Reentrant and thread-safe

 */
        final int LENGTH = 10_000_000;
        final int HALF = LENGTH / 2;

        float[] resource = new float[LENGTH];
        Arrays.fill(resource, 1.0f);
        long time = 0;

        time = System.currentTimeMillis();
        for (int i = 0; i < resource.length; i++) {
            resource[i] = (float) (resource[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println(System.currentTimeMillis() - time);

        time = System.currentTimeMillis();
        float[] resource2 = new float[HALF];
        float[] resource3 = new float[HALF];
        System.arraycopy(resource, 0, resource2, 0, HALF);
        System.arraycopy(resource, HALF, resource3, 0, HALF);
        Thread thread1 = new Thread(new BodyOfThread(resource2));
        Thread thread2 = new Thread(new BodyOfThread(resource3));
        thread1.start();// recalling a start() after the end of thread is an error.
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException exc) {
            exc.printStackTrace();
        }
        System.arraycopy(resource2, 0, resource, 0, HALF);
        System.arraycopy(resource3, 0, resource, HALF, HALF);
        System.out.println(System.currentTimeMillis() - time);
    }
}
