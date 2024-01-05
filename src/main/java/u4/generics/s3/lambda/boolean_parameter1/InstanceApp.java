package u4.generics.s3.lambda.boolean_parameter1;

import u4.generics.s3.lambda.Human;

public class InstanceApp {
    public static void main(String[] args) {

        Human hum = new Human("HUM");
        Human other = new Human("OTHER");

        /*
        Reference to an instance method of a particular object
        containingObject::instanceMethodName
        myComparisonProvider::compareByName , myApp::appendStrings2
        */
        Predicate_b_p1<Human> predicateBp1 = hum::takeHuman;  // mister hum will handle other!
        predicateBp1 = new Predicate_b_p1<Human>() {
            @Override
            public boolean test(Human being) {
                //!return hum.takeVoid(being)
                return hum.takeHuman(being);
            }
        };
        predicateBp1.test(other);
    }
}
