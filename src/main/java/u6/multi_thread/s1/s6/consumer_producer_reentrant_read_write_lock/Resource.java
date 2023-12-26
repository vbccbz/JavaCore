package u6.multi_thread.s1.s6.consumer_producer_reentrant_read_write_lock;

public class Resource {
    int n = 0;

    void put(int n) {
        this.n = n;
        return;
    }

    int get() {
        return this.n;
    }
}
