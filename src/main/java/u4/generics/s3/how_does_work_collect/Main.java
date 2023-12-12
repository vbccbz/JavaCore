package u4.generics.s3.how_does_work_collect;

public class Main {
    public static <R, A> R myCollect(MyCollector<String, A, R> myCollector) {
        return myCollector.action();
    }

    public static <T> MyCollector<T, ?, MyList<T>> toMyList() {
        return new MyCollector<T, T, MyList<T>>() {
            @Override
            public MyList<T> action() {
                return null;
            }
        };
    }

    public static <T> MyCollector<T, ?, MyMap<T>> toMyMap() {
        return new MyCollector<T, T, MyMap<T>>() {
            @Override
            public MyMap<T> action() {
                return null;
            }
        };
    }

    public static void main(String[] args) {
        MyList<String> listik = myCollect(toMyList());
        MyMap<String> mapik = myCollect(toMyMap());






    }
}
