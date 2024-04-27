package u4.generics.s0;
/*
void func(int i){}
i is a formal value parameter

func(123);
123 is an actual argument (sad, but not "actual value argument")
 */


public class MyGen<T> {
    /*
    public class MyGen<T>{ }
    is a generic type declaration (it is compiled once).
    A class is generic if it declares one or more type variables (§4.4).

    <T> is the formal type parameter section.
    This is a declaration of the formal type parameter T ( type variables, параметр типа, тИповый параметр).
    A type variable is an unqualified identifier used as a type in class, interface, static and non-static methods, and constructor bodies.

    4.4 Type Variables
        A type variable is an unqualified identifier used as a type in class, interface, method,
        and constructor bodies.
        A type variable is introduced by the declaration of a type parameter of a generic
        class, interface, method, or constructor (§8.1.2, §9.1.2, §8.4.4, §8.8.4).
        TypeParameter:
        {TypeParameterModifier} Identifier [TypeBound]

    4.5 Parameterized Types
        A class or interface declaration that is generic (§8.1.2, §9.1.2) defines a set of
        parameterized types.
        A parameterized type is a class or interface type of the form C<T1,...,Tn>, where C
        is the name of a generic type and <T1,...,Tn> is a list of type arguments that denote
        a particular parameterization of the generic type.

    8.1.2 Generic Classes and Type Parameters
        A class is generic if it declares one or more type variables (§4.4).
        These type variables are known as the type parameters of the class. The type
        parameter section follows the class name and is delimited by angle brackets.
        TypeParameters:
    < TypeParameterList >
        TypeParameterList:
        TypeParameter {, TypeParameter}
    */
    T data;

    public void add(T data) {
        this.data = data;
    }

    public T get() {
        return data;
    }

    @Override
    public boolean equals(Object obj) {
//        return super.equals(obj);
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        MyGen<?> o = (MyGen<?>) obj;
        if (this.data == null) return false;
        return this.data.equals(o.data);
    }
}
