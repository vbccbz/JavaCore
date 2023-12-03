package hello;

import org.joda.time.LocalTime;

public class Hellower {
    public static void main(String[] args) {
        LocalTime currentTime = new LocalTime();
        System.out.println(currentTime);
        Greeter greeter = new Greeter();
        System.out.println(greeter.getHello());
    }
}
