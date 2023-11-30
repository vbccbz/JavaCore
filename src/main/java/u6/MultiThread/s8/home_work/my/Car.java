package u6.MultiThread.s8.home_work.my;

import java.util.concurrent.*;

public class Car implements Runnable {
    private static int CARS_COUNT;

    static {
        CARS_COUNT = 0;
    }

    private StagesContainerRace stagesContainer;
    private int speed;
    private String name;

    public Car(StagesContainerRace stagesContainer, int speed) {
        this.stagesContainer = stagesContainer;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    @Override
    public void run() {
        try {
            System.out.println(name + " готовится");
            Thread.sleep(500 + (int) (Math.random() * 800));
            System.out.println(name + " готов, жду остальных");
            MainClassApp.barrierPrepare.await();
            MainClassApp.barrierPrepare.await();
        } catch (InterruptedException | BrokenBarrierException exception) {
            exception.printStackTrace();
        }
        for (int i = 0; i < stagesContainer.getStages().size(); i++) {
            stagesContainer.getStages().get(i).go(this);
        }
        stagesContainer.setWinner(name);
        MainClassApp.latchAllRace.countDown();
    }
}
