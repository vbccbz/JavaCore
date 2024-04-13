package u4.generics.s1.copying;

public class App {

    public static <T> void copy(Container<? extends T> src, Container<? super T> dst) {
    }

    public static void main(String[] args) {
        Container<Shape> shapes = new Container<>();
        Container<Circle> circles = new Container<>();

        System.out.println(shapes.getClass().getName());
        System.out.println(circles.getClass().getName());

        App.<Shape>copy(circles, shapes);
        App.<Circle>copy(circles, shapes);
        //!copy(shapes, circles);
        copy(circles, shapes);
    }
}
