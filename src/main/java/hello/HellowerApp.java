package hello;

import org.joda.time.LocalTime;

public class HellowerApp {
    public static void f(Object o){
        if (o.getClass() == Integer.class) System.out.println("it is Integer");
        if (o.getClass() == String.class) System.out.println("it is String");
    }
    public static void main(String[] args) {
//        LocalTime currentTime = new LocalTime();
//        System.out.println(currentTime);
//        Greeter greeter = new Greeter();
//        System.out.println(greeter.getHello());
        f("lol");
        f(213);
    }
}
