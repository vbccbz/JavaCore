package u4.generics.s3.references;

public class App {
    public static boolean takeVoid() {
        System.out.println("void");
        return false;
    }

    public static boolean takeHuman(Human human) {
        System.out.println(human.name);
        return true;
    }

    public static boolean compareHumans(Human human, Human human2){
        return true;
    }

    public static void main(String[] args) {
        boolean result = false;

        ConstructRef<Human> cr = Human::new;
        Human example = cr.func("New");

        Human first = new Human("first");
        Human second = new Human("second");
        Human third = new Human("third");

        /*
        Reference to a static method
        ContainingClass::staticMethodName
        Person::compareByAge , MethodReferencesExamples::appendStrings
        */

        Consumer_v_p1<Human> consumer = null;
        Predicate_b_p1<Human> predicate = null;
        Predicate_b_p1_p2<Human, Human> predicate2 = null;

        predicate = new Predicate_b_p1<Human>() {
            @Override
            public boolean test(Human human) {
                //!return StaticMethodApp.takeVoid(human);
                return App.takeHuman(human);
            }
        };

        consumer = new Consumer_v_p1<Human>() {
            @Override
            public void accept(Human human) {
                //!StaticMethodApp.takeVoid(human);
                App.takeHuman(human);
                return;
            }
        };

        /*
        Reference to an instance method of a particular object
        containingObject::instanceMethodName
        myComparisonProvider::compareByName , myApp::appendStrings2
        */

        predicate = new Predicate_b_p1<Human>() {
            @Override
            public boolean test(Human human) {
                //!return first.takeVoid(human)
                return first.takeHuman(human);
            }
        };

        consumer = new Consumer_v_p1<Human>() {
            @Override
            public void accept(Human human) {
                //!first.takeVoid(human);
                first.takeHuman(human);
                return;
            }
        };
        //wtf first = new Human();//Variable 'first' is accessed from within inner class, needs to be final or effectively final

        /*
        Reference to an instance method of an arbitrary object of a particular type
        ContainingType::methodName
        String::compareToIgnoreCase , String::concat
        */

        predicate = new Predicate_b_p1<Human>() {
            @Override
            public boolean test(Human human) {
                return human.takeVoid();
                //!return human.takeHuman();
            }
        };
        consumer = new Consumer_v_p1<Human>() {
            @Override
            public void accept(Human human) {
                human.takeVoid();
                //!human.takeHuman();
                return;
            }
        };

        predicate2 = new Predicate_b_p1_p2<Human, Human>() {
            @Override
            public boolean test(Human human, Human human2) {
                return human.takeHuman(human2);
            }
        };

        predicate2 = new Predicate_b_p1_p2<Human, Human>() {
            @Override
            public boolean test(Human human, Human human2) {
                return third.compareHumans(human, human2);
            }
        };



        consumer = App::takeHuman;
        consumer.accept(first);//App.takeHuman(first)

        consumer = first::takeHuman;
        consumer.accept(second);//first.takeHuman(second)

        consumer = Human::takeVoid;
        consumer.accept(first);//first.takeVoid()

        predicate = App::takeHuman;
        result = predicate.test(first);//App.takeHuman(first)

        predicate = first::takeHuman;
        result = predicate.test(second);//first.takeHuman(second)

        predicate = Human::takeVoid;
        result = predicate.test(first);// first.takeVoid()

        predicate2 = App::compareHumans;
        predicate2.test(first, second);//App.compareHumans(first,second)

        predicate2 = third::compareHumans;
        result = predicate2.test(first, second);//third.compareHumans(first, second)

        predicate2 = Human::takeHuman;
        result = predicate2.test(first, second);//first.takeHuman(second)

    }
}
