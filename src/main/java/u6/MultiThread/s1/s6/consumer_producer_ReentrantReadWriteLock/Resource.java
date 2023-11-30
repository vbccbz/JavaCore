package u6.MultiThread.s1.s6.consumer_producer_ReentrantReadWriteLock;

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
