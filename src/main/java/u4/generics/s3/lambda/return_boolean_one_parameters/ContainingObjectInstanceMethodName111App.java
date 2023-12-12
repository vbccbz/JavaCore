package u4.generics.s3.lambda.return_boolean_one_parameters;

public class ContainingObjectInstanceMethodName111App {
    public static void main(String[] args) {

        Human hum = new Human("HUM");
        Human other = new Human("OTHER");

        MyPredicateWithOneParameters<Human> myPredicateWithOneParameters = hum::takeHuman;  // mister hum will handle other!
        myPredicateWithOneParameters = new MyPredicateWithOneParameters<Human>() {
            @Override
            public boolean test(Human being) {
                return hum.takeHuman(being);// therefore takeHuman() return type MUST correspond with interface method return type.
            }
        };


        myPredicateWithOneParameters.test(other);


        /*
        Судя по всему, это может быть объект любого класса, лишь бы его метод подходил.
        Reference to an instance method of a particular object
        containingObject::instanceMethodName
        myComparisonProvider::compareByName , myApp::appendStrings2
        */

    }
}
