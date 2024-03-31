package u11.decorator;

public class App {
    public static void main(String[] args) {
        Decor2 decor2 = new Decor2(new Decor1(new Imp()));
        decor2.read();

        Decor1 decor1 = new Decor1(new Decor2(new Imp()));
        decor1.read();
    }
}
