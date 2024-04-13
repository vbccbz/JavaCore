package u4.generics.s0.vararg;

/*
[I
[[I
java.lang.Integer
[Ljava.lang.Integer
[[Ljava.lang.Integer
*/

public class GenArrVarArgApp {
    static <T> void element(T element) {
        System.out.println(element.getClass().getName());
    }

    static <T> void array(T[] array) {
        System.out.println(array.getClass().getName());
    }

    static <T> void varArg(T... varg) {// If the last formal parameter is a variable arity parameter of type T, it is considered to define a formal parameter of type T[] ; JLS 8.4.1 Format parameters
        System.out.println(varg.getClass().getName());
    }

    public static void main(String[] args) {
        int[] ints = new int[3];
        Integer[] integers = new Integer[3];

        element(ints); // [I
        element(integers);// [Ljava.lang.Integer;

        //!array(ints);// wtf
        array(integers);// [Ljava.lang.Integer;

        varArg(ints);// [[I wtf
        varArg(integers);// [Ljava.lang.Integer;

        varArg(1,2,3);// [Ljava.lang.Integer;

        GenArrVarArgApp.<Object>varArg(1,2,3);// [Ljava.lang.Object;

        Object o = 1;
        System.out.println(o.getClass().getName());// java.lang.Integer
    }
}


