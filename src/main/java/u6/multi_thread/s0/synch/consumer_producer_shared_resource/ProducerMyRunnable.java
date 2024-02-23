package u6.multi_thread.s0.synch.consumer_producer_shared_resource;

class ProducerMyRunnable implements Runnable {
    Resource resource;

    public ProducerMyRunnable(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        System.out.println("start producer.run()" + Thread.currentThread().getName());
        for (int i = 0; i < 5; ++i) {
            resource.put(i);
        }
        System.out.println("end producer.run()" + Thread.currentThread().getName());
    }
}
