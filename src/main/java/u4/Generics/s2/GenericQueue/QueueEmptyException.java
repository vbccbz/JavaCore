package u4.Generics.s2.GenericQueue;

public class QueueEmptyException extends Exception {
    @Override
    public String toString() {
        return "Queue is empty";
    }
}
