package u4.Generics.s2.GenericQueue;

public interface IGenQ <T> {
    void put(T ch) throws QueueFullException;
    T get() throws QueueEmptyException;
}