package u4.generics.s3.boolean_parameter1;

import u4.generics.s3.Human;

public class StaticMethodApp {
    public static boolean takeVoid() {
        System.out.println("void");
        return false;
    }

    public static boolean takeHuman(Human h) {
        System.out.println(h.name);
        return true;
    }

    public static void main(String[] args) {

        Human hum = new Human("HUM");
        Human other = new Human("OTHER");

        /*
        Reference to a static method
        ContainingClass::staticMethodName
        Person::compareByAge , MethodReferencesExamples::appendStrings
        */
        Predicate_b_p1<Human> predicateBp1 = StaticMethodApp::takeHuman;
        predicateBp1 = new Predicate_b_p1<Human>() {
            @Override
            public boolean test(Human being) {
                //!return StaticMethodApp.takeVoid(being);
                return StaticMethodApp.takeHuman(being);
            }
        };
        predicateBp1.test(hum);

    }
}
