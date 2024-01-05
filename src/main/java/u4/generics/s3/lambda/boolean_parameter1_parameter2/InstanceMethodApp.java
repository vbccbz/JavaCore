package u4.generics.s3.lambda.boolean_parameter1_parameter2;

import u4.generics.s3.lambda.Human;

public class InstanceMethodApp {
    public static void main(String[] args) {

        Human hum = new Human("HUM");
        Human other = new Human("OTHER");

        Predicate_b_p1_p2<Human, Human> face =  Human::takeHuman;

        boolean result = face.test(hum, other);// return hum.takeHuman(other);

    }
}
