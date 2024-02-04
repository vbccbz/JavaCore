package u4.generics.s3.nested_classes;

public class NestedClassesApp {
    int field = 1;

    class A {
        int field = NestedClassesApp.this.field;

        void hi() {
            System.out.println("Hello!");
        }
    }

    A a;

    Object c = new Object() {
        int field = NestedClassesApp.this.field;

        void hi() {
            System.out.println("Hello!");
        }
    };

    public void met() {
        class D {
            int field = NestedClassesApp.this.field;

            void hi() {
                System.out.println("Hello!");
            }
        }
        D d;

        Object e = new Object() {
            int field = NestedClassesApp.this.field;

            void hi() {
                System.out.println("Hello!");
            }
        };

    }

    public static void main(String[] args) {
        int local = 2;

        class F {
            int local = 2;

            void hi() {
                System.out.println("Hello!");
            }
        }
        F f;

        Object g = new Object() {
            int local = 2;

            void hi() {
                System.out.println("Hello!");
            }
        };
    }
}
