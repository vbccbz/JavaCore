package u4.Generics.s1.GenericQueue;

public interface IGenQ <T> {
    void put(T ch) throws QueueFullException;
    T get() throws QueueEmptyException;
}