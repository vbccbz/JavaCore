package u4.Generics.s3.Lambda.returnVoid;

public class ContainingClassStaticMethodNameApp {
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

        MyConsumerWithOneParameters<Human> humanMyConsumerWithOneParameters = null;

        humanMyConsumerWithOneParameters = ContainingClassStaticMethodNameApp::takeHuman;// accept() just discards returned boolean :-)
//!     humanMyConsumerWithOneParameters = ContainingClassStaticMethodNameApp::takeVoid;

        humanMyConsumerWithOneParameters.accept(hum);

    }
}
