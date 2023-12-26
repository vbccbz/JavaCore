package u6.multi_thread.s6.collections_queue_producer_consumer_WTF;


public class CollectionConcurrencyApp {
    public static void main(String[] args) {
/*
Lists all aren't synchronized.
    Use sync outside,
    Or use old vector,
    Or Collections synchronizedTYPE have methods for wrapping Lists and Maps into synchronized.

Maps aren't synchronized.
    Use sync outside,
    or use Hashtable (but it can't read and write simultaneously),
    or use ConcurrentHashMap (non-block reading, smart writing).

CopyOnWriteArrayList
    Задача - список объект, с которым работают треды. Если тред начал работать с листом, то он должен его видеть в том виде, в котором он начал его видеть.
    Если параллельно с ним другой тред добавляет в коллекцию элементы - то создаётся новый лист и второй тред будет работать с новым листом.
    Первый тред будет продолжать работать со старым вариантом.

ReadBlockingQueue
    For Producer-Consumer. Set size.
        Producer:
            put() - put element; if queue is full, set thread in wait()
            offer() - if successful (plus timeout if needed) it returns true, if does't - false.
            add() - if doesn' succefull, throws exception
        Consumer:
            take() - take and delete from queue; if queue is empty, set thread in wait()
            poll() - returns element; if queue is empty (plus timeout if needed), returns null.
            peek() - just to see.
    ChainedRBQ
           A --operation1--> B --operation2--> C --operation3--> D
                   T0                T1                T2


 */
    }
}
