package u6.multi_thread.s1.s7.consumer_producer_reentrant_read_write_lock_ticktock;

public class TickTock {
    String status = "none";

    public void tick() {
        if (status.equals("ticked")) {
            System.out.println("if");
            MainApp.second.lock();
        } else {
            try {
                MainApp.first.lock();
                System.out.println("tick");
                Thread.sleep(500);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            } finally {
                status = "ticked";
                MainApp.first.unlock();
            }
        }
    }

    public void tock() {

    }

}
