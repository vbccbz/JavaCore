package u4.generics.s0;

import java.util.*;

public class PecsApp {
    public static void main(String[] args) {
        SomeClass1 c1 = new SomeClass1();
        SomeClass2 c2 = new SomeClass2();
        SomeClass3 c3 = new SomeClass3();

        Gen<? extends SomeClass2> prod = null;
        Gen<? super SomeClass2> cons = null;

//!        prod = new Gen<SomeClass1>();
        prod = new Gen<SomeClass2>();//prod = new Gen<>();//The type parameter T is inferred based on the argument type
        prod = new Gen<SomeClass3>();

//!        prod.add(c1);
//!       prod.add(c2);
//!        prod.add(c3);

        c1 = prod.get();
        c2 = prod.get();
//!        c3 = prod.get();

        cons = new Gen<SomeClass1>();
        cons = new Gen<SomeClass2>();
//!        cons = new Gen<SomeClass3>();

//!        cons.add(c1);
        cons.add(c2);
        cons.add(c3);

//!        c1 = cons.get();
//!        c2 = cons.get();
//!        c3 = cons.get();

    }
}
