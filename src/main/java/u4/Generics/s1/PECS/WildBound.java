package u4.Generics.s1.PECS;

import java.util.*;

public class WildBound {
    public static void main(String[] args) {
        SomeClass1 c1;
        SomeClass2 c2;
        SomeClass3 c3;

        Gen<? extends SomeClass2> prod = new Gen<>();
//!        prod.add(new Class1());
//!        prod.add(new Class2());
//!        prod.add(new Class3());
        c1 = prod.get();
        c2 = prod.get();
//!        c3 = prod.get();

        List<? extends SomeClass2> producer = new ArrayList<>();
//!        producer.add(new Class1() );
//!        producer.add(new Class2() );
//!        producer.add(new Class3() );
        c1 = producer.get(0);
        c2 = producer.get(0);
//!        c3 = producer.get(0);

        Gen<? super SomeClass2> cons = new Gen<>();
//!        cons.add(new Class1());
        cons.add(new SomeClass2());
        cons.add(new SomeClass3());
        c1 = prod.get();
        c2 = prod.get();
//!        c3 = prod.get();

        List<? super SomeClass2> consumer = new ArrayList<>();
//!        consumer.add(new Class1());
        consumer.add(new SomeClass2());
        consumer.add(new SomeClass3());
//!        c1 = consumer.get(0);
//!        c2 = consumer.get(0);
//!        c3 = consumer.get(0);

    }
}
