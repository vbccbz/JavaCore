package u4.Generics.s3.returnBooleanTwoParamteres;

@FunctionalInterface
public interface MyPredicateWithTwoParameters<T1, T2> {
    boolean test(T1 t1, T2 t2);
}
