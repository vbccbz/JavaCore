package u6.multi_thread.s1.s1.consumer_producer_ticktock;

//tick() notifies tock() therefore lets tock() run
// пока не равен tocked - нужно спать. tocked может измениться только в  только метод tock(). Если уведомит что-то другое - цикл снова переведёт в wait().
// tick() waits for tock() to complete

public class TickTock {
    String state;// state = "none"

    synchronized public void tick(boolean running) {
        if (running) {
            /**********************************************/ // action
            System.out.println("tick");
            try {
                Thread.sleep(500);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
            state = "ticked";
            notify();
            /**********************************************/ // waiting
//            while (!state.equals("tocked")) {
            while (state.equals("ticked")) {
                try {
                    wait();//This causes the thread to go to sleep and the monitor for that object to be released, allowing another thread to use the object. At a later point, the sleeping thread is awakened when some other thread enters the same monitor and calls notify( ), or notifyAll( ).
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
            }
            return;
        } else {
            /**********************************************/ // action
            state = "ticked";
            notify();
            return;
        }
    }

    synchronized public void tock(boolean running) {
        if (running) {
            /**********************************************/ // action
            System.out.println("tock");
            try {
                Thread.sleep(500);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
            state = "tocked";
            notify();
            /**********************************************/ // waiting
            while (!state.equals("ticked")) {
                try {
                    wait();
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
            }
            return;
        } else {
            /**********************************************/ // action
            state = "tocked";
            notify();
            return;
        }
    }
}
