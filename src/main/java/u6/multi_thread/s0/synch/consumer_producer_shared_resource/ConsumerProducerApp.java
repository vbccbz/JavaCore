package u6.multi_thread.s0.synch.consumer_producer_shared_resource;

public class ConsumerProducerApp {
    public static void main(String[] args) {
        Resource resource = new Resource();

        Thread t1 = new Thread(new ConsumerMyRunnable(resource));
        Thread t2 = new Thread(new ProducerMyRunnable(resource));

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
