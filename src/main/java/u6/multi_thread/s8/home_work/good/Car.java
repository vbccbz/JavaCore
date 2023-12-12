package u6.multi_thread.s8.home_work.good;

import static u6.multi_thread.s8.home_work.good.MainClassApp.countDownLatch;
import static u6.multi_thread.s8.home_work.good.MainClassApp.cyclicBarrier;

public class Car implements Runnable {
    private static int CARS_COUNT;
    private static boolean winner;

    static {
        CARS_COUNT = 0 ;
    }
    private Race race;
    private int speed;

private String name;
    public String getName () {
        return name;
    }
    public int getSpeed () {
        return speed;
    }
    public Car (Race race, int speed) {
        this .race = race;
        this .speed = speed;
        CARS_COUNT++;
        this .name = "Участник #" + CARS_COUNT;
    }
    @Override
    public void run () {
        try {
            System.out.println( this .name + " готовится" );
            Thread.sleep( 500 + ( int )(Math.random() * 800 ));
            System.out.println( this .name + " готов" );
            countDownLatch.countDown();
            cyclicBarrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for ( int i = 0 ; i < race.getStages().size(); i++) {
            race.getStages().get(i).go( this );
        }
        checkWinner(this);
    }

    private static synchronized void checkWinner(Car c) {
        if (!winner) {
            System.out.println(c.name + " - WIN");
            winner = true;
        }
    }
}