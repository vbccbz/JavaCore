package u4.Generics.s3.Lambda.NestedClasses;

public class NestedClassesApp {
    int instance = 1;

    class A { int field = instance;}
    A a;

    Object c = new Object() {
        int field = instance;
        void hi() { System.out.println("Hello!"); }
    };

    public void met() {
        class D { int field = instance; }
        D d;

        Object e = new Object() {
            int field = instance;
            void hi() { System.out.println("Hello!"); }
        };

    }

    public static void main(String[] args) {
        int local = 2;

        class F { int local = 2; }
        F f;

        Object g = new Object() {
            int local = 2;
            void hi() { System.out.println("Hello!"); }
        };
    }
}
