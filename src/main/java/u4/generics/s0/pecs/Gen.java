package u4.generics.s0.pecs;

public class Gen<T> {
    T t;
    public void add(T t){ this.t = t;}
    public T get(){ return t; }
}
