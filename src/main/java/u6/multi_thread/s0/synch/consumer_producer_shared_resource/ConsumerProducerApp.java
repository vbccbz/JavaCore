package u6.multi_thread.s0.synch.consumer_producer_shared_resource;

public class ConsumerProducerApp {
    public static void main(String[] args) {
        Thread t1 = null;
        Thread t2 = null;

        Resource resource = new Resource();

//        ProducerMyRunnable p = new ProducerMyRunnable(resource);
//        t1 = new Thread(p);
        t1 = new Thread(() -> {
            for (int i = 0; i < 5; ++i) {
                resource.put(i);
            }
        });

//        ConsumerMyRunnable c = new ConsumerMyRunnable(resource);
//        t2 = new Thread(c);
        t2 = new Thread(() -> {
            for (int i = 0; i < 5; ++i) {
                resource.get();
            }
        });

        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }
}
