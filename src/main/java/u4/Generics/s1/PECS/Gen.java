package u4.Generics.s1.PECS;

public class Gen<T> {
    T t;
    public void add(T t){ this.t = t;}
    public T get(){ return t; }
}
