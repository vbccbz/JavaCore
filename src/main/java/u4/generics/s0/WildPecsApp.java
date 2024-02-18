package u4.generics.s0;

import java.util.*;

public class WildPecsApp {

    public static void main(String[] args) {
        Gen<?> wildBox = null;
        /* ? is a special kind of type argument
        Collection<?>
        (pronounced "collection of unknown") is the supertype of all kinds of collection (is a wildcard type).
        Any parameter we pass to add() would have to be a subtype of this unknown type.
        Since we don't know what type that is, we cannot pass anything in.
        On the other hand, given a List<?>, we can call get() and make use of the result.
        The result type is an unknown type, but we always know that it is an Object.*/

        SomeClass1 c1 = new SomeClass1();
        SomeClass2 c2 = new SomeClass2();
        SomeClass3 c3 = new SomeClass3();

        Gen<? extends SomeClass2> producer = null;
        Gen<? super SomeClass2> consumer = null;

//!     producer = new Gen<SomeClass1>();
        producer = new Gen<SomeClass2>();
        producer = new Gen<SomeClass3>();

        consumer = new Gen<SomeClass1>();
        consumer = new Gen<SomeClass2>();
//!     consumer = new Gen<SomeClass3>();

//!     producer.add(c1);
//!     producer.add(c2);
//!     producer.add(c3);

//!     consumer.add(c1);
        consumer.add(c2);
        consumer.add(c3);

        c1 = producer.get();
        c2 = producer.get();
//!     c3 = producer.get();

//!     c1 = consumer.get();
//!     c2 = consumer.get();
//!     c3 = consumer.get();

    }
}
