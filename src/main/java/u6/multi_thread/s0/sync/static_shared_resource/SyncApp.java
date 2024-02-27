package u6.multi_thread.s0.sync.static_shared_resource;

public class SyncApp {
    public static void main(String[] args) {

        int data1[] = {1, 2, 3, 4, 5};
        int data2[] = {11, 22, 33, 44, 55};
        MyRunnable mts1 = MyRunnable.createAndStart("1", data1);
        MyRunnable mts2 = MyRunnable.createAndStart("2", data2);


        try {
            mts1.thrd.join();
            mts2.thrd.join();
        } catch (InterruptedException exception) {
            System.out.println(exception);
        }
    }
}
