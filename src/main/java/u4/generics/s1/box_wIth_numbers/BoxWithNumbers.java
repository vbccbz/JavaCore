package u4.generics.s1.box_wIth_numbers;


public class BoxWithNumbers<N extends Number> {// any BoxWithNumbers will be at least BoxWithNumbers<Number>
    private N[] array;

    public BoxWithNumbers(N... array) {
        this.array = array;
    }

    public double average() {
        double sum = 0.0;
        for (int i = 0; i < array.length; ++i) {
            sum += array[i].doubleValue();
            // 1. Object cannot call subclass methods, but Number can.
            // 2. Auto boxing doesn't work in Generic
        }
        return sum / array.length;
    }

    // ( BoxWithNumbers other )
    // ( BoxWithNumbers<String> other )
    // ( BoxWithNumbers<T> other )
    public boolean wildCompareAverage(BoxWithNumbers<?> other) {// <? extends Number> is redundant here, wildcard is argument in invokation and doesn't correlate with N.
        double diff = average() - other.average();
        return (Math.abs(diff) < 0.0001);
    }

}
