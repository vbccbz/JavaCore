package u4.generics.s1.generic_queue;

public interface IGenQ <T> {
    void put(T ch) throws QueueFullException;
    T get() throws QueueEmptyException;
}