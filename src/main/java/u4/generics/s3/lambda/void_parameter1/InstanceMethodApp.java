package u4.generics.s3.lambda.void_parameter1;

import u4.generics.s3.lambda.Human;

public class InstanceMethodApp {

    public static void main(String[] args) {
        Human hum = new Human("HUM");

        Consumer_v_p1<Human> humanConsumerVp1 = null;
        humanConsumerVp1 = Human::takeVoid;
        humanConsumerVp1 = new Consumer_v_p1<Human>() {
            @Override
            public void accept(Human human) {
                human.takeVoid();
                //!human.takeHuman();
                return;
            }
        };
        humanConsumerVp1.accept(hum);

    }
}
