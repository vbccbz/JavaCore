package u3.nested_classes;

public class OuterClass {
    private int field = 1;

    public class NestedInnerMemberClass {
        int field = OuterClass.this.field;

        void hi() {
            System.out.println("Hello!");
        }
    }

    public static class NestedStaticClass {
        //!int field = OuterClass.this.field;

        void hi() {
            System.out.println("Hello!");
        }
    }


    Object c = new Object() {
        int field = OuterClass.this.field;

        void hi() {
            System.out.println("Hello!");
        }
    };

    public void met() {
        class D {
            int field = OuterClass.this.field;

            void hi() {
                System.out.println("Hello!");
            }
        }
        D d;

        Object e = new Object() {
            int field = OuterClass.this.field;

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
