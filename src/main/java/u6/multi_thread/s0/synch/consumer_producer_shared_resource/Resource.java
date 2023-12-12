package u6.multi_thread.s0.synch.consumer_producer_shared_resource;

class Resource {
    int n = 0;

    synchronized void put(int n) {
        System.out.println("start put:" + Thread.currentThread().getName());
        this.n = n;
        try {
            Thread.sleep(150);
        } catch (InterruptedException exc) {
            exc.printStackTrace();
        }
        System.out.println("end put:" + Thread.currentThread().getName());
        return;
    }

    synchronized int get() {
        System.out.println("\t\tstart get:" + Thread.currentThread().getName());
        try {
            Thread.sleep(300);
        } catch (InterruptedException exc) {
            exc.printStackTrace();
        }
        System.out.println("\t\tend get:" + Thread.currentThread().getName());
        return this.n;
    }
}
