package u4.generics.s3.references;

public class Utilz<T> {
    public static <T> boolean stakeVoid() {
        return false;
    }

    public static <T> boolean stake(T t) {
        return true;
    }

    public static <T> boolean scompare(T t1, T t2) {
        return true;
    }

    public boolean compare(T t1, T t2) {
        return true;
    }
}
