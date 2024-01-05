package u4.generics.s0;

/*
[I
[[I
java.lang.Integer
[Ljava.lang.Integer
[[Ljava.lang.Integer
*/

public class TElementApp {

    static <T> void element(T element) {
    }

      public static void main(String[] args) {

        TElementApp.<int[]>element(new int[]{1, 2, 3});
        TElementApp.<Integer[]>element(new Integer[]{1, 2, 3});

    }
}
