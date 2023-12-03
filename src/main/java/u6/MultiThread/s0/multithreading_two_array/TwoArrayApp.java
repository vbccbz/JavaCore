package u6.MultiThread.s0.multithreading_two_array;

import java.util.Arrays;

public class TwoArrayApp {
    public static void main(String[] args) {
/*
Process
    Code(Text)
    Data(statics)
    Files(???)
    Threads
        Registers
        Stack
        Each thread has separate heap block.

True parallel can work only on at least 2 core.

Objects and classes have the Monitor. The Monitor is an abstraction for synchronizing. Synchronized doesn't mean "simultaneously executing". It means "synchronized access to code - only one thread can process this code.

At first, MyRunnable looks more logical than SomeClassName. But for real - many classes implements many interfaces. And their names isn't correspond with interface's names. The implementation of interface endows attributes to a type(class). Instead "MyRunnable" you can use "ArrayHandler".

_Thread_local int tmp; does copy of static block in thread.
Reentrant and thread-safe

 */
        final int LENGTH = 10_000_000;
        final int HALF = LENGTH / 2;

        float[] floats = new float[LENGTH];
        Arrays.fill(floats, 1.0f);

        long time = 0;
        time = System.currentTimeMillis();

        for (int i = 0; i < floats.length; i++) {
            floats[i] = (float) (floats[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) *
                    Math.cos(0.4f + i / 2));
        }

        System.out.println(System.currentTimeMillis() - time);
        time = System.currentTimeMillis();

        float[] floats1 = new float[HALF];
        float[] floats2 = new float[HALF];
        System.arraycopy(floats, 0, floats1, 0, HALF);
        System.arraycopy(floats, HALF, floats2, 0, HALF);

        Thread thread1 = new Thread(new MyRunnable(floats1));
        Thread thread2 = new Thread(new MyRunnable(floats2));

        thread1.start();// recalling a start() after the end of thread is an error.
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException exc) {
            exc.printStackTrace();
        }
        System.arraycopy(floats1, 0, floats, 0, HALF);
        System.arraycopy(floats2, 0, floats, HALF, HALF);

        System.out.println(System.currentTimeMillis() - time);
    }
}
