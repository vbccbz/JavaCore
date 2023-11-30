package u6.MultiThread.s4.s2.Semaphore;

import java.util.concurrent.Semaphore;

public class SimpleSemaphoreApp {
    public static void main(String[] args) {

        final Semaphore smp = new Semaphore(2);
        /*
        Too much threads can slow an executing.
        How to restrict the quantity of threads that can connect to some synchronized block?

         */

//        Runnable limitedCall = new Runnable() {
//            private int count = 0;
//
//            @Override
//            public void run() {
//                int num = count++;
//                try {
//                    smp.acquire();
//
//                    synchronized (this) {
//                        for (int i = 0; i < num; ++i) {
//                            System.out.print('\t');
//                        }
//                        System.out.println(num);
//                    }
//
//                    Thread.sleep(1000);
//
//                    synchronized (this) {
//                        for (int i = 0; i < num; ++i) {
//                            System.out.print('\t');
//                        }
//                        System.out.println(num);
//                    }
//
//
//                } catch (InterruptedException intEx) {
//                    intEx.printStackTrace();
//                } finally {
//                    smp.release();
//                }
//            }
//        };
//
//        for (int i = 0; i < 9; i++) {
//            new Thread(limitedCall).start();
//        }



        for (int i = 0; i < 9; i++) {
            final int number = i;

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(number + " before acquire ");
                        smp.acquire();
                        System.out.println(number + " got ");
                        Thread.sleep(1000);
                    } catch (InterruptedException intEx) {
                        intEx.printStackTrace();
                    } finally {
                        System.out.println(number);
                        System.out.println(number + " released ");

                        smp.release();
                    }
                }
            }).start();
        }
    }
}
