package u6.multi_thread.s8.home_work.my.stages;

import u6.multi_thread.s8.home_work.my.Car;

public class Road extends Stage {
    public Road(int length) {
        this.length = length;
        this.description = "Дорога " + length + " метров";
    }

    @Override
    public void go(Car c) {
        System.out.println(c.getName() + " начал этап: " + description);
        try {
            Thread.sleep(length / c.getSpeed() * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(c.getName() + " закончил этап: " + description);
    }
}
