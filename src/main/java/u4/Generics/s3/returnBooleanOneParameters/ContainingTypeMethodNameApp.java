package u4.Generics.s3.returnBooleanOneParameters;

public class ContainingTypeMethodNameApp {
    public static void main(String[] args) {

        Human hum = new Human("HUM");
        Human other = new Human("OTHER");

        //!!! STRANGE THING DOESN'T WORK !!!
//!     MyPredicate<Human> myPredicate = Human::takeHuman;// due to the lack of second parameter in MyPredicate.
        /*
        myPredicate = new MyPredicate<Human>() {
            @Override
            public boolean test(Human being) {
                return being.takeHuman(???); // !!!
            }
        };
        */

        MyPredicateWithOneParameters<Human> myPredicateWithOneParameters = Human::takeVoid;// takeVoid is called easy with other :-)
        myPredicateWithOneParameters = new MyPredicateWithOneParameters<Human>() {
            @Override
            public boolean test(Human being) {
                return being.takeVoid();
            }
        };


        myPredicateWithOneParameters.test(other);

        /*
        Reference to an instance method of an arbitrary object of a particular type
        ContainingType::methodName
        String::compareToIgnoreCase , String::concat
        */

    }
}
