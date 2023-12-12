package u4.generics.s1.generic_queue;

public class QueueFullException extends Exception {
    @Override
    public String toString() {
        return "Queue is full";
    }
}
