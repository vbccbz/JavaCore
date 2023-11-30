package u6.MultiThread.s1.s2.atomic_operations_race_condition;

public class SynchCounterApp {
    public static void main(String[] args) {
        SynchCounter synchCounter = new SynchCounter();

        Thread incThread = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                synchCounter.inc();
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread decThread = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                synchCounter.dec();
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        incThread.start();
        decThread.start();
        try {
            incThread.join();
            decThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Значение синхр. счетчика должно быть 0, иначе значит race condition : " + synchCounter.getValue());
    }
}
