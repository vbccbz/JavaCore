package u6.MultiThread.s8.home_work.good;


public abstract class Stage {
    protected int length;
    protected String description;
    public String getDescription () {
        return description;
    }
    public abstract void go (Car c);
}