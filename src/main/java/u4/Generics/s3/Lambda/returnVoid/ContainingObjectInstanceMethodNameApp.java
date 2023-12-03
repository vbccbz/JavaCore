package u4.Generics.s3.Lambda.returnVoid;

public class ContainingObjectInstanceMethodNameApp {

    public static void main(String[] args) {
        Human hum = new Human("HUM");// Second object isn't need for consumer logic

        MyConsumerWithOneParameters<Human> humanMyConsumerWithOneParameters = null;

        humanMyConsumerWithOneParameters = hum::takeHuman;
        humanMyConsumerWithOneParameters = new MyConsumerWithOneParameters<Human>() {
            @Override
            public void accept(Human human) {
                hum.takeHuman(human);// SO strange for consumer
                return;
            }
        };
//!     humanMyConsumerWithOneParameters = hum::takeVoid;

        humanMyConsumerWithOneParameters.accept(hum);// returned boolean are just discarded :-)

    }
}
