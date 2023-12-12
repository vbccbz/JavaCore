package u6.multi_thread.s8.home_work.my;

import u6.multi_thread.s8.home_work.my.Stages.Road;
import u6.multi_thread.s8.home_work.my.Stages.Tunnel;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class MainClassApp {
    public static final int CARS_COUNT = 4;
    public static CyclicBarrier barrierPrepare;
    public static CountDownLatch latchAllRace;
    public static Semaphore semaphore;

    public static void main(String[] args) {
        barrierPrepare = new CyclicBarrier(CARS_COUNT + 1);
//        barrierPrepare = new CyclicBarrier(CARS_COUNT, ()->{
//            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
//        });
        latchAllRace = new CountDownLatch(CARS_COUNT);
        semaphore = new Semaphore(CARS_COUNT / 2);

        StagesContainerRace stagesContainer = new StagesContainerRace(new Road(60), new Tunnel(), new Road(80));

        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(stagesContainer, 20 + (int) (Math.random() * 10));
        }

        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }
        try {
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
            barrierPrepare.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
            barrierPrepare.await();
            latchAllRace.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
        } catch (InterruptedException | BrokenBarrierException exception) {
            exception.printStackTrace();
        }

    }
}
