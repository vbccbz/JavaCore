package u4.Generics.s3.returnVoid;

public class ContainingTypeMethodNameApp {

    public static void main(String[] args) {
        Human hum = new Human("HUM");// Second object isn't need for consumer logic

        MyConsumerWithOneParameters<Human> humanMyConsumerWithOneParameters = null;

//!     humanMyConsumerWithOneParameters = Human::takeHuman;
//      humanMyConsumerWithOneParameters = new MyConsumerWithOneParameters<Human>() {
//         @Override
//         public void accept(Human human) {
//             human.takeHuman(???);
//             return;
//         }
//      };

        humanMyConsumerWithOneParameters = Human::takeVoid;
        humanMyConsumerWithOneParameters = new MyConsumerWithOneParameters<Human>() {
            @Override
            public void accept(Human human) {
                human.takeVoid();
                return;
            }
        };

        humanMyConsumerWithOneParameters.accept(hum);// returned boolean are just discarded :-)

    }
}
