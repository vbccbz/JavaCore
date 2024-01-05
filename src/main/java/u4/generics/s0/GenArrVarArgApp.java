package u4.generics.s0;

/*
[I
[[I
java.lang.Integer
[Ljava.lang.Integer
[[Ljava.lang.Integer
*/

public class GenArrVarArgApp {
    static <T> void element(T element) {
    }
    static <T> void varArg(T... array) {// If the last formal parameter is a variable arity parameter of type T, it is considered to define a formal parameter of type T[] ; JLS 8.4.1 Format parameters
    }
    static <T> void array(T[] array) {
    }

    public static void main(String[] args) {
        GenArrVarArgApp.<int[]>element(new int[]{1, 2, 3});// T = int[]
        GenArrVarArgApp.<Integer[]>element(new Integer[]{1, 2, 3});// T = Integer

        //!GenArrVararg.<int>array(new int[]{1,2,3});// T != int
        GenArrVarArgApp.<Integer>array(new Integer[]{1, 2, 3});// T = Integer

        //!GenArrVarArgApp.<int>varArg(new int[]{1, 2, 3});
        GenArrVarArgApp.<Integer>varArg(1, 2, 3);// autoboxing, length = 3, new Integer[]{1, 2, 3}

        // !!!ATTENTION!!!
        GenArrVarArgApp.<int[]>varArg(new int[]{1, 2, 3});
    }
}
