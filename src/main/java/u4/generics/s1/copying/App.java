package u4.generics.s1.copying;

public class App {

    public static <T> void copy(Container<? super T> dst, Container<? extends T> src) {
    }

    public static void main(String[] args) {
        Container<Shape> shapes = new Container<>();
        Container<Circle> circles = new Container<>();

        System.out.println(shapes.getClass().getName());
        System.out.println(circles.getClass().getName());

//!        App.<Shape>copy(circles, shapes);
//!        App.<Circle>copy(circles, shapes);
//!        copy(circles, shapes);
        copy(shapes, circles);
    }
}
