package u4.generics.s1.generic_queue;

public class GenQueue<T> implements IGenQ<T> {
    private T[] q;
    private int size;
    private int first;
    private int last;

    public GenQueue(T[] arr) {
//        q = new T[s];
//        size = s;
        q = arr;
        size = arr.length;
        first = 0;
        last = -1;
    }

    @Override
    public void put(T ch) throws QueueFullException {
        if (last == size - 1) throw new QueueFullException();
        ++last;
        q[last] = ch;
    }

    @Override
    public T get() throws QueueEmptyException {
        if (last < first) throw new QueueEmptyException();
        T data = q[first];
        ++first;
        return data;
    }
}
