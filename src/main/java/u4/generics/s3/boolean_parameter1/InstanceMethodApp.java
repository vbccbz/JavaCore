package u4.generics.s3.boolean_parameter1;

import u4.generics.s3.Human;

public class InstanceMethodApp {
    public static void main(String[] args) {

        Human hum = new Human("HUM");
        Human other = new Human("OTHER");

        /*
        Reference to an instance method of an arbitrary object of a particular type
        ContainingType::methodName
        String::compareToIgnoreCase , String::concat
        */
        Predicate_b_p1<Human> predicateBp1 = Human::takeVoid;
        predicateBp1 = new Predicate_b_p1<Human>() {
            @Override
            public boolean test(Human being) {
                return being.takeVoid();
                //!return being.takeHuman();
            }
        };
        predicateBp1.test(other);



    }
}
