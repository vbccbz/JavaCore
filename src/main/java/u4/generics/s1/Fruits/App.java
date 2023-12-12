package u4.generics.s1.Fruits;

import java.lang.*;

public class App {

    public static void main(String[] args) {

        FruitBox<Apple> appleFruitBox = new FruitBox<>();
        appleFruitBox.add(new Apple(1.0f));
        appleFruitBox.add(new Apple(1.0f));
        appleFruitBox.add(new Apple(1.0f));

        FruitBox<Orange> orangeFruitBox = new FruitBox<>();
        orangeFruitBox.add(new Orange(1.5f));
        orangeFruitBox.add(new Orange(1.5f));

        FruitBox<Fruit> fruitBox = new FruitBox<>();
        orangeFruitBox.fillTo(fruitBox);
        appleFruitBox.fillTo(fruitBox);

    }
}
