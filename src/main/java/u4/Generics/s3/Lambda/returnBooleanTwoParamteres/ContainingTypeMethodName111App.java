package u4.Generics.s3.Lambda.returnBooleanTwoParamteres;

public class ContainingTypeMethodName111App {
    public static void main(String[] args) {

        Human hum = new Human("HUM");
        Human other = new Human("OTHER");

        MyPredicateWithTwoParameters<Human, Human> face =  Human::takeHuman;

        boolean result = face.test(hum, other);// hum.takeHuman(other);

    }
}
