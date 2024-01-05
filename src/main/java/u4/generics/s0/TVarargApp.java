package u4.generics.s0;

/*
[I
[[I
java.lang.Integer
[Ljava.lang.Integer
[[Ljava.lang.Integer
*/

public class TVarargApp {

    static <T> void varArg(T... array) {// If the last formal parameter is a variable arity parameter of type T, it is considered to define a formal parameter of type T[] ; JLS 8.4.1 Format parameters
    }

    public static void main(String[] args) {

        TVarargApp.<Integer>varArg(1, 2, 3);// autoboxing, length = 3
        TVarargApp.<Integer>varArg(Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(3));// length = 3
        TVarargApp.<Integer>varArg(new Integer[]{1, 2, 3});// length = 3

        TVarargApp.<int[]>varArg(new int[]{1, 2, 3});// length = 1
        TVarargApp.<Integer[]>varArg(new Integer[]{1, 2, 3});// length = 1

    }
}
