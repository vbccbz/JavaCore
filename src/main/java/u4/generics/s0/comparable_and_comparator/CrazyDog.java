package u4.generics.s0.comparable_and_comparator;

public class CrazyDog extends Animal implements MComparable<Animal> {
    @Override
    public int cmp(Animal animal) {
        return 0;
    }
}
