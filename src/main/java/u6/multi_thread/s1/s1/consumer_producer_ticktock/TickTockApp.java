package u6.multi_thread.s1.s1.consumer_producer_ticktock;

public class TickTockApp {
    public static void main(String[] args) {
        TickTock tt = new TickTock();// one object for all with synchronized methods
        MyRunnablePlusThread tickThread = MyRunnablePlusThread.createAndStart("Tick", tt);
        MyRunnablePlusThread tockThread = MyRunnablePlusThread.createAndStart("Tock", tt);
        try {
            tickThread.thrd.join();
            tockThread.thrd.join();
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }
}
