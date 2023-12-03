package u4.Generics.s3.Lambda.returnVoid;

@FunctionalInterface
public interface MyConsumerWithOneParameters<T> {
    void accept(T t);
}
