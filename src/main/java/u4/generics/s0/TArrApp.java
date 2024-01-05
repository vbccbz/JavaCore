package u4.generics.s0;

/*
[I
[[I
java.lang.Integer
[Ljava.lang.Integer
[[Ljava.lang.Integer
*/

public class TArrApp {

    static <T> void array(T[] array) {
    }

    public static void main(String[] args) {

        TArrApp.<int[]>array(new int[2][3]);//
        TArrApp.<Integer>array(new Integer[]{1, 2, 3});
    }
}
