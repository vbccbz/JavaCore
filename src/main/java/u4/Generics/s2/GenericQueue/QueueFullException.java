package u4.Generics.s2.GenericQueue;

public class QueueFullException extends Exception {
    @Override
    public String toString() {
        return "Queue is full";
    }
}
