package u4.generics.s3.references;

public class App {
    public static void main(String[] args) {
        boolean result = false;

        ConstructRef<Human> cr = Human::new;
        Human example = cr.func("New");

        Human first = new Human("first");
        Human second = new Human("second");
        Utilz utilz = new Utilz();

        /*
        Reference to a static method
        ContainingClass::staticMethodName
        Person::compareByAge , MethodReferencesExamples::appendStrings
        */
        
        /*
        Reference to an instance method of a particular object
        containingObject::instanceMethodName
        myComparisonProvider::compareByName , myApp::appendStrings2
        */
        
        /*
        Reference to an instance method of an arbitrary object of a particular type
        ContainingType::methodName
        String::compareToIgnoreCase , String::concat
        */

        /*
        Reference to a static method    ContainingClass::staticMethodName   Person::compareByAge
        MethodReferencesExamples::appendStrings

        Reference to an instance method of a particular object  containingObject::instanceMethodName    myComparisonProvider::compareByName
        myApp::appendStrings2

        Reference to an instance method of an arbitrary object of a particular type ContainingType::methodName  String::compareToIgnoreCase
        String::concat

        Reference to a constructor  ClassName::new  HashSet::new
        */

        Consumer_v_p1<Human> consumer = null;
        Predicate_b_p1<Human> predicate = null;
        Predicate_b_p1_p2<Human, Human> predicate2 = null;

        consumer = Utilz::takeHuman;
        consumer.accept(first);//Utilz.takeHuman(first)

        consumer = first::compare;
        consumer.accept(second);//first.compare(second)

        consumer = Human::takeVoid;
        consumer.accept(first);//first.takeVoid()

        predicate = Utilz::takeHuman;
        result = predicate.test(first);//Utilz.takeHuman(first)

        predicate = first::compare;
        result = predicate.test(second);//first.compare(second)

        predicate = Human::takeVoid;
        result = predicate.test(first);// first.takeVoid()

        predicate2 = Utilz::scompareHumans;
        predicate2.test(first, second);//Utilz.scompareHumans(first,second)

        predicate2 = utilz::compareHumans;
        result = predicate2.test(first, second);//utilz.compareHumans(first, second)

        predicate2 = Human::compare;
        result = predicate2.test(first, second);//first.compare(second)

    }
}
