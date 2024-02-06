package u2.inheritance;

import java.io.*;

public class BaseOopApp {
    public static void main(String[] args) {
        A a = new A();
        B b = new B();

        a.athod();
        a.othod();

        b.athod();
        b.othod();
        b.bethod();

        a = b;
        a.athod();
        a.othod();

        DataInputStream dis = null;
//        dis.read();
    }
}
