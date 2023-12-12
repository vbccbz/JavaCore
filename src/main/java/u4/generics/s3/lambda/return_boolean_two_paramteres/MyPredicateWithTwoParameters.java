package u4.generics.s3.lambda.return_boolean_two_paramteres;

@FunctionalInterface
public interface MyPredicateWithTwoParameters<T1, T2> {
    boolean test(T1 t1, T2 t2);
}
