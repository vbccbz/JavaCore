package u6.MultiThread.s1.s2.atomic_operations_race_condition;

public class SynchCounter {
    private int v;

    public int getValue() { return v; }

    public SynchCounter() {
        v = 0;
    }

    // try not to sync)))
    public synchronized void inc() {
        v++;
    }
    // V++ : 1) read c 2) add one 3) write c

    public synchronized void dec() {
        v--;
    }
}