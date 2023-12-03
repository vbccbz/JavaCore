package u9.Annotations;

import java.lang.reflect.*;
import java.lang.annotation.*;

@MyAnnotation(strangeValue = 1)
public class Meta {

    @MyAnnotation(strangeValue = 2)
    public void instanceMethod() {
        System.out.println("instanceMethod");
    }

    @MyAnnotation(strangeValue = 3)
    public static void staticMethod() {
        System.out.println("staticMethod");
    }


    public static void main(String[] args) {
        Meta ob = new Meta();
        for (Annotation a : ob.getClass().getAnnotations()) {
            System.out.println(a);
        }

        for (Method m: ob.getClass().getDeclaredMethods()) {
            System.out.println(m);
            System.out.print(m.getName() + " ");
            for ( Annotation a : m.getAnnotations()){
                System.out.print(a + " ");
            }
            System.out.println();
        }


    }
}




