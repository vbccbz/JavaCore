package u4.Generics.s1.GenericQueue;

public class QueueFullException extends Exception {
    @Override
    public String toString() {
        return "Queue is full";
    }
}
