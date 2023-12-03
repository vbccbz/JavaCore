package u4.Generics.s0.FormalTypeParameters;

/**
 *
 * void func(int i){}
 *     i is a formal value parameter
 *
 * func(123);
 *     123 is an actual argument (sad, but not "actual value argument")
 *
 * <T>
 *     <T> is the formal type parameter section.
 *     This is a declaration of the formal type parameter T ( type variables, параметр типа, тИповый параметр).
 *     A type variable is an unqualified identifier used as a type in class, interface, static and non-static methods, and constructor bodies.
 *
 * public class BoxWithSome<T>{ }
 *     is a generic type declaration (it is compiled once).
 *     A class is generic if it declares one or more type variables (§4.4).
 *
 * Box<Integer> integerBox;
 *     is a generic type invocation (an invocation of a generic type) with an actual type argument Integer.
 *     Therefore, the declaration denotes a parameterized type.
 *
 * new Box<Integer>();
 *     is a generic type instantiation (an instantiation of a generic type Box) with actual type argument Integer.
 *
 * OrderedPair<String, Box<Integer>> p;
 *     is a substituting a type parameter K with non-primitive type String and V with a parameterized type List<String>.
 *
 * ?
 *     is a special kind of type argument (sometimes it is referred like "the actual type parameter", but it's not cool).
 *
 * Collection<?>
 *     (pronounced "collection of unknown") is the supertype of all kinds of collection (is a wildcard type).
 *     Any parameter we pass to add() would have to be a subtype of this unknown type.
 *     Since we don't know what type that is, we cannot pass anything in.
 *     On the other hand, given a List<?>, we can call get() and make use of the result.
 *     The result type is an unknown type, but we always know that it is an Object.
 *
 */

public class BoxWithSome<T> {
    public T t;

    public void set(T t) {

    }

    public T get() {
        return t;
    }

}
