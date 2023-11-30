package u4.Generics.s1.GenericQueue;

public class QueueEmptyException extends Exception {
    @Override
    public String toString() {
        return "Queue is empty";
    }
}
