package u4.generics.s3.lambda.return_void;

@FunctionalInterface
public interface MyConsumerWithOneParameters<T> {
    void accept(T t);
}
