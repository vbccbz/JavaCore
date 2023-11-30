package u4.Generics.s1.GenVarArgObjectArray;

public class MainApp {
    static <T> void element(T array) {
        System.out.println(array.getClass().getName());
    }

    static <T> void array(T[] array) {
        System.out.println(array.getClass().getName());

        T t = array[0];// T is more significant thing than T[].
    }

    static <T> void varArg(T... array) {
        System.out.println(array.getClass().getName() + " " + array.length);
    }
    /*
    The type parameter T is inferred based on the argument type
    The last formal parameter in a list is special; it may be a variable arity parameter, indicated by an elipsis following the type.
    If the last formal parameter is a variable arity parameter of type T, it is considered to define a formal parameter of type T[] ; JLS 8.4.1 Format parameters
     */

    /*
    [I
    [[I
    java.lang.Integer
    [Ljava.lang.Integer
    [[Ljava.lang.Integer
     */

    public static void main(String[] args) {
        /*
        10.8. Class Objects for Arrays
        Every array has an associated Class object, shared with all other arrays with the same component type.
        Although an array type is not a class, the Class object of every array acts as if:
            * The direct superclass of every array type is Object.
            * Every array type implements the interfaces Cloneable and java.io.Serializable.
         */
        Object obj = null;
        Object[] objs = null;

        A a = new A();
        B b = new B();
        int i = 0;// primitives are not classes so they don't inherit from Object.
        int[] ints = new int[]{1, 2, 3};// int[] is not a class, but is like a class.
        A[] as = new A[3];
        B[] bs = new B[3];// is instanceof A[]

        obj = i;// autoboxing to Integer
        obj = objs = as = bs;
//!        obj = objs = ints;
        obj = ints;// int[] acts like "class"

        element(Integer.valueOf(1));//T ---> Integer
        element(1);//autoboxing ---> T ---> Integer

        element(new int[3]);// T ---> int[]
        element(new Integer[3]);// T ---> Integer[]

//        array(new int[3]);//splits int from [] ---> int ---> int cannot work with generics
//        MainApp.<int>array(new int[3]);//splits int from [] ---> int ---> int cannot work with generics
        array(new Integer[3]);// splits Integer from [] ---> T [] ---> Integer[]
        MainApp.<Integer>array(new Integer[3]);

        varArg(new int[3]);//treats int[] as a single argument of int[] type ---> T... ---> int[]... ---> int[][]
        varArg(new Integer[3]);// splits Integer from [] ---> T... ---> Integer... ---> Integer[]
        varArg(1, 2, 3);// autoboxing Integer ---> T... ---> Integer... ---> Integer[]

        MainApp.<Integer>varArg(new Integer[3]);// Integer ---> T... ---> Integer... ---> Integer[]
        MainApp.<Integer[]>varArg(new Integer[3]);// Integer[] --> T... ---> Integer[]... ---> Integer[][]


        int[][][] zyx = {
                {
                        {0, 1, 2},
                        {3, 4, 5},
                        {6, 7, 8}
                },
                {
                        {10, 11, 12},
                        {13, 14, 15},
                        {16, 17, 18}
                },
                {
                        {20, 21, 22},
                        {23, 24, 25},
                        {26, 27, 28}
                }
        };
        System.out.println(zyx[0][2][2]);
        System.out.println(zyx[2][1][0]);

    }
}
