package u4.Generics.s1.Fruits;

import java.util.*;

public class FruitBox<T extends Fruit> {
    private List<T> arrayList;

    public FruitBox() {
        arrayList = new ArrayList<>();
    }

    public FruitBox(T... fruits) {
        arrayList = new LinkedList<>(Arrays.asList(fruits));
    }

    public void add(T fruit) {
        arrayList.add(fruit);
    }

    public void addAll(T... fruits) {
        arrayList.addAll(Arrays.asList(fruits));
    }

    public T get(int index) {
        return arrayList.get(index);
    }

    public double getWeight() {
        double sum = 0;
        for (T element : arrayList) {
            sum += element.getWeight();
        }
        return sum;
    }

    public boolean compare(FruitBox<?> other) {
        return Math.abs(getWeight() - other.getWeight()) < 0.0001;
    }

    public void fillTo(FruitBox<? super T> other) {
        if (this == other) {
            return;
        }
//        for (int i = 0; i < this.arrayList.size(); ++i) {
//            other.arrayList.add(this.arrayList.get(i));
//        }
//        while (arrayList.size() != 0) {
//            arrayList.remove(0);
//        }
        other.arrayList.addAll(arrayList);
        arrayList.clear();

    }
}
