package u4.generics.s0;
/*
void func(int i){}
i is a formal value parameter

func(123);
123 is an actual argument (sad, but not "actual value argument")
 */

public class Gen<T> {
/*<T> is the formal type parameter section.
This is a declaration of the formal type parameter T ( type variables, параметр типа, тИповый параметр).
A type variable is an unqualified identifier used as a type in class, interface, static and non-static methods, and constructor bodies.*/

    /*public class Gen<T>{ }
    is a generic type declaration (it is compiled once).
    A class is generic if it declares one or more type variables (§4.4).*/
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
        Gen<?> o = (Gen<?>) obj;
        if (this.data == null) return false;
        return this.data.equals(o.data);
    }
}
