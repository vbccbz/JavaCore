package u6.MultiThread.s7.priority;

public class MyThread implements Runnable {
    Thread thrd;

    int count;

    static boolean stop = false;
    static String currentName;

    public MyThread(String name){
        thrd = new Thread(this, name);
        count = 0;
        currentName = name;
    }

    @Override
    public void run() {
        System.out.print ("Start:" + thrd.getName() + " ");
        do {
            count++;
            if (currentName.compareTo(thrd.getName()) != 0){
                currentName = thrd.getName();
                System.out.print(currentName + " ");
            }
        }while (stop == false && count < 10000000 );
        stop = true;
        System.out.print ("End:" + thrd.getName() + " ");
    }
}
