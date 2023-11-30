package u6.MultiThread.s8.home_work.my.Stages;

import u6.MultiThread.s8.home_work.my.Car;
import u6.MultiThread.s8.home_work.my.MainClassApp;

public class Tunnel extends Stage {
    public Tunnel() {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
    }

    @Override
    public void go(Car c) {

        try {
            if (!MainClassApp.semaphore.tryAcquire()) {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                MainClassApp.semaphore.acquire();
            }
            System.out.println(c.getName() + " начал этап: " + description);
            Thread.sleep(length / c.getSpeed() * 1000);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        } finally {
            System.out.println(c.getName() + " закончил этап: " + description);
            MainClassApp.semaphore.release();
        }

    }
}
