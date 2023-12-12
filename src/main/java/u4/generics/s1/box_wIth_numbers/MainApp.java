package u4.generics.s1.box_wIth_numbers;

public class MainApp {
    public static void wild(BoxWithNumbers<? extends Integer> w) {// not a generic at all
        ;
    }

    public static <T> void kek(BoxWithNumbers<? extends Integer> w) {// useless generic
        ;
    }

    public static <T extends Integer> void wild2(BoxWithNumbers<T> w) {
        ;
    }

    public static void main(String[] args) {
        BoxWithNumbers<?> reference;// type of reference is BoxWithNumbers<?>
        //! new BoxWithNumbers<?>();// type of object cannot be BoxWithNumbers<?>
        // type of reference != type of object
        // A refa = null;
        // refa = new A();
        // refa = new B();
        BoxWithNumbers<Integer> i = new BoxWithNumbers<>(2, 2, 2);// from 1.8 version can not write type in <> after new.
        BoxWithNumbers<Double> d = new BoxWithNumbers<>(2.0, 2.0, 2.0);

        System.out.println(i.getClass().getName());// VTB.Unit4Generics1.Base.BoxWithNumbers
        System.out.println(d.getClass().getName());// VTB.Unit4Generics1.Base.BoxWithNumbers

        i.wildCompareAverage(d);
        d.wildCompareAverage(i);

        wild(i);
//!        wild(d);
        wild2(i);
//!        wild2(d);


    }
}
