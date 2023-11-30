package u6.MultiThread.s1.s3.consumer_producer_trafficlight;

public class TrafficLightApp {
    public static void main(String[] args) {
        MyRunnablePlusThread tl = MyRunnablePlusThread.createAndRun(MyRunnablePlusThread.TrafficLightColor.RED);
        System.out.println("mainstart:" + Thread.currentThread().getName());
        for (int i = 0; i < 6; ++i) {
            System.out.println(tl.getTlc());
            tl.waitUntilChanged();
//            System.out.println(tl.getTlc());
        }
        System.out.println("mainend:" + Thread.currentThread().getName());
        tl.stop();

    }
}



