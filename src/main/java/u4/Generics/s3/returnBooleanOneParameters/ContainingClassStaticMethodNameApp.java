package u4.Generics.s3.returnBooleanOneParameters;

public class ContainingClassStaticMethodNameApp {
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

        MyPredicateWithOneParameters<Human> myPredicateWithOneParameters = ContainingClassStaticMethodNameApp::takeHuman;
        myPredicateWithOneParameters = new MyPredicateWithOneParameters<Human>() {
            @Override
            public boolean test(Human being) {
                return ContainingClassStaticMethodNameApp.takeHuman(being);
            }
        };
//      MyPredicateWithOneParameters<Human> myPredicateWithOneParameters = ContainingClassStaticMethodNameApp::takeVoid;// cannot take argument from test()

        myPredicateWithOneParameters.test(hum);

        /*
        Reference to a static method
        ContainingClass::staticMethodName
        Person::compareByAge , MethodReferencesExamples::appendStrings
        */
    }
}
