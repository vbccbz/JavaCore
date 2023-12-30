package u4.generics.s0;

import java.lang.reflect.Array;

public class JavaArrApp {
    public static void arrTest(int[] array){}

    public static void main(String[] args) {
        Object object = null;
        Object[] objects = null;
        int [] ints = null;
        Number[] numbers = null;
        Integer[] integers = null;

        object = objects = numbers = integers;
        object = ints;

        int s = Array.getLength(object);
        object = Array.get(object, 0);

        /*
        10.8. Class Objects for Arrays
        Every array has an associated Class object, shared with all other arrays with the same component type.
        Although an array type is not a class, the Class object of every array acts as if:
            * The direct superclass of every array type is Object.
            * Every array type implements the interfaces Cloneable and java.io.Serializable.
         */

        int[] array1 = new int[]{1,2,3};
        int[] array2 = {1,2,3};
        arrTest(new int[]{1,2,3});
        //!arrTest({1,2,3});//array initializer isn't allowed here
        // but something like that works in annotations

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
