package u4.generics.s3.references;

@FunctionalInterface
public interface BPP<T1, T2> {
    boolean test(T1 t1, T2 t2);
}
