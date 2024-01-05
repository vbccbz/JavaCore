package u4.generics.s3.lambda.void_parameter1;

import u4.generics.s3.lambda.Human;

public class StaticMethodApp {
    public static boolean takeVoid() {
        System.out.println("void");
        return false;
    }

    public static boolean takeHuman(Human h) {
        System.out.println(h.name);
        return true;
    }

    public static void returnNothing(Human h){
        return;
    }

    public static void main(String[] args) {
        Human hum = new Human("HUM");// Second object isn't need for consumer logic

        Consumer_v_p1<Human> humanConsumerVp1 = null;
        //!humanConsumerVp1 = StaticMethodApp::takeVoid;
        humanConsumerVp1 = StaticMethodApp::takeHuman;
        humanConsumerVp1.accept(hum);

    }
}
