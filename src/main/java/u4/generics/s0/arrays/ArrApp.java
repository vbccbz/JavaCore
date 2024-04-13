package u4.generics.s0.arrays;

import java.lang.reflect.Array;

public class ArrApp {
    public static void arrTest(int[] array) {
    }

    public static void main(String[] args) {
        Object object = null;

        int[] ints = new int[3];
        Object[] objects = null;
        Number[] numbers = null;
        Integer[] integers = new Integer[3];

        object = ints;
        object = objects = numbers = integers;

        int length = Array.getLength(object);
        object = Array.get(object, 0);

        /*
        10.8. Class Objects for Arrays
        Every array has an associated Class object, shared with all other arrays with the same component type.
        Although an array type is not a class, the Class object of every array acts as if:
            * The direct superclass of every array type is Object.
            * Every array type implements the interfaces Cloneable and java.io.Serializable.
         */

        int[] array1 = {1, 2, 3};
        //!array1 = {1,2,3};
        arrTest(new int[]{1, 2, 3});
        //!arrTest({1,2,3});//array initializer isn't allowed here
        // but something like that works in annotations

        String[][][] zyx = {
                {
                        {"000", "001", "002"},
                        {"010", "011", "012"},
                        {"020", "021", "022"}
                },
                {
                        {"100", "101", "102"},
                        {"110", "111", "112"},
                        {"120", "121", "122"}
                },
                {
                        {"200", "201", "202"},
                        {"210", "211", "212"},
                        {"220", "221", "222"}
                }
        };
    }
}
