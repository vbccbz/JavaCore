package u6.MultiThread.s2.s2.exceptions_suspend_resume_stopped_interrupted;

public class SuspendResumeStopInterruptedApp {
    public static void doSomething() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MyRunnable mt = MyRunnable.createAndRun();

        doSomething();
        mt.suspend();
        doSomething();
        mt.resume();
        doSomething();
        mt.stop(); // do not mistake with mt.thrd.stop();

        try {
            mt.thrd.join();
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }

    }
}
