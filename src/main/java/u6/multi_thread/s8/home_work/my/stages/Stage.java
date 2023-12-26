package u6.multi_thread.s8.home_work.my.stages;

import u6.multi_thread.s8.home_work.my.Car;

public abstract class Stage {
    protected int length;
    protected String description;

    public String getDescription() {
        return description;
    }

    public abstract void go(Car c);
}
