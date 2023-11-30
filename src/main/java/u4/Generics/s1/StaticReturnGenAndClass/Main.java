package u4.Generics.s1.StaticReturnGenAndClass;

import java.util.*;

public class Main {
    public static <T> Gen<T> factory() {
        return new Gen<T>();
    }

    public void m(Object o) {
        Gen<String> gen = factory();
        gen = Main.<String>factory();

        List<Integer> concreteType = Collections.emptyList();
        concreteType = java.util.Collections.<Integer>emptyList();
        concreteType.add(123);

        String str = "kak";

        Class<String> one = String.class;
        Class<?> two = String.class;
        Class three = String.class;

//!        one = str.getClass();
        two = str.getClass();
        three = str.getClass();
    }
}
