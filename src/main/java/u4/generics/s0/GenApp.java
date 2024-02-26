package u4.generics.s0;

public class GenApp {
    public static void main(String [] args){
        Gen<SomeClass2> box1 = null;// declaration
        /*Gen<Integer> is a parameterized type. Gen<SomeClass2> box1 is a generic type invocation (an invocation of a generic type) with an actual type argument Integer. Or "substituting a type parameter T with non-primitive type Integer".*/
        box1 = new Gen<SomeClass2>();
        /*new Gen<SomeClass2>() is a generic type instantiation (an instantiation of a generic type Gen) with actual type argument Integer.*/

        Gen<SomeClass2> box2 = new Gen<>();
        /*The type parameter T is inferred based on the argument type*/

        Object object = box1;

    }
}
