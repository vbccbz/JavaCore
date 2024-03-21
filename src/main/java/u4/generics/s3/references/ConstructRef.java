package u4.generics.s3.references;

@FunctionalInterface
public interface ConstructRef<T> {
    T func(String str);
}
