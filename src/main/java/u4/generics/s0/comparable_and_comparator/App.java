package u4.generics.s0.comparable_and_comparator;

public class App {

//    static <T> void sort(MList<T> list, MComparator<T> comparator) {
    static <T> void sort(MList<T> list, MComparator<? super T> comparator) {
        comparator.compare(list.data1, list.data2);
    }

    //!static <T> void sort(MList<? extends MComparable<T>> list) {// cannot .cmp()
    //!static <T extends MComparable<T>> void sort(MList<T> list) {// cannot sort(dogs)
    static <T extends MComparable<? super T>> void sort(MList<T> list) {
        list.data1.cmp(list.data2);
    }

    public static void main(String[] args) {
        MList<Animal> animals = new MList<>();
        MList<CrazyDog> dogs = new MList<>();

        sort(animals, new ComparatorAnimalImp());//MComparator<T> comparator and MComparator<? super T> comparator
//        sort(dogs, new ComparatorAnimalImp());//MComparator<? super T> comparator only

//        sort(animals);
        sort(dogs);


    }
}
