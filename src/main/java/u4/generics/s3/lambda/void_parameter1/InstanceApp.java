package u4.generics.s3.lambda.void_parameter1;

import u4.generics.s3.lambda.Human;

public class InstanceApp {

    public static void main(String[] args) {
        Human hum = new Human("HUM");// Second object isn't need for consumer logic

        Consumer_v_p1<Human> humanConsumerVp1 = hum::takeHuman;
        humanConsumerVp1 = new Consumer_v_p1<Human>() {
            @Override
            public void accept(Human human) {
                //!hum.takeVoid(human);
                hum.takeHuman(human);// SO strange for consumer
                return;// returned boolean are just discarded :-)
            }
        };
        humanConsumerVp1.accept(hum);

    }
}
