package u6.multi_thread.s0.synch.consumer_producer_shared_resource;

class ConsumerMyRunnable implements Runnable {
    Resource resource;

    public ConsumerMyRunnable(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        System.out.println("\t\tstartRunConsumer:" + Thread.currentThread().getName());
        for (int i = 0; i < 5; ++i) {
            resource.get();
        }
        System.out.println("\t\tendRunConsumer:" + Thread.currentThread().getName());
    }
}
