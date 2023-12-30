package u4.generics.s0;

/*
The type parameter T is inferred based on the argument type
The last formal parameter may be a variable arity parameter, indicated by an elipsis following the type.
If the last formal parameter is a variable arity parameter of type T, it is considered to define a formal parameter of type T[] ; JLS 8.4.1 Format parameters

[I
[[I
java.lang.Integer
[Ljava.lang.Integer
[[Ljava.lang.Integer

*/

public class TArrTVarArgApp {
    static <T> void element(T element) {
        System.out.println(element.getClass().getName());
    }

    static <T> void array(T[] array) {
        System.out.println(array.getClass().getName());
        T t = array[0];// T is more significant thing than T[].
    }

    static <T> void varArg(T... array) {
        System.out.println(array.getClass().getName() + " " + array.length);
    }

    public static void main(String[] args) {

        element(Integer.valueOf(1));//T ---> Integer
        element(1);//autoboxing ---> T ---> Integer
        element(new int[3]);// T ---> int[]
        element(new Integer[3]);// T ---> Integer[]

//        array(new int[3]);//splits int from [] ---> int ---> int cannot work with generics
//        MainApp.<int>array(new int[3]);//splits int from [] ---> int ---> int cannot work with generics
        array(new Integer[3]);// splits Integer from [] ---> T [] ---> Integer[]
        TArrTVarArgApp.<Integer>array(new Integer[3]);

        varArg(new int[3]);//treats int[] as a single argument of int[] type ---> T... ---> int[]... ---> int[][]
        varArg(new Integer[3]);// splits Integer from [] ---> T... ---> Integer... ---> Integer[]
        TArrTVarArgApp.<Integer>varArg(new Integer[3]);// Integer ---> T... ---> Integer... ---> Integer[]
        varArg(1, 2, 3);// autoboxing Integer ---> T... ---> Integer... ---> Integer[]
        TArrTVarArgApp.<Integer[]>varArg(new Integer[3]);// Integer[] --> T... ---> Integer[]... ---> Integer[][]

    }
}
