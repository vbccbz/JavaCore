package u4.generics.s0;

public class GenApp {
    public static void main(String [] args){
        Gen<Integer> box1 = null;
        /*Gen<Integer> box1 is a generic type invocation (an invocation of a generic type) with an actual type argument Integer. Or "substituting a type parameter T with non-primitive type Integer". Therefore, the declaration denotes a parameterized type Gen<Integer>.*/
        box1 = new Gen<Integer>();
        /*new Gen<Integer>() is a generic type instantiation (an instantiation of a generic type Gen) with actual type argument Integer.*/

        Gen<Integer> box2 = new Gen<>();//The type parameter T is inferred based on the argument type

        Object object = box1;

    }
}
