package u4.generics.s3.references;

public class App {
    public static boolean takeVoid() {
        System.out.println("void");
        return false;
    }

    public static boolean takeHuman(Human h) {
        System.out.println(h.name);
        return true;
    }

    public static void main(String[] args) {
        boolean result = false;

        Human hum = new Human("HUM");
        Human other = new Human("OTHER");

        /*
        Reference to a static method
        ContainingClass::staticMethodName
        Person::compareByAge , MethodReferencesExamples::appendStrings
        */

        Predicate_b_p1<Human> predicate = App::takeHuman;
        Consumer_v_p1<Human> consumer = App::takeHuman;
        ;

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

        result = predicate.test(hum);
        consumer.accept(hum);

        /*
        Reference to an instance method of a particular object
        containingObject::instanceMethodName
        myComparisonProvider::compareByName , myApp::appendStrings2
        */
        predicate = hum::takeHuman;
        consumer = hum::takeHuman;

        predicate = new Predicate_b_p1<Human>() {
            @Override
            public boolean test(Human human) {
                //!return hum.takeVoid(human)
                return hum.takeHuman(human);
            }
        };
        consumer = new Consumer_v_p1<Human>() {
            @Override
            public void accept(Human human) {
                //!hum.takeVoid(human);
                hum.takeHuman(human);
                return;
            }
        };
        //wtf hum = new Human();//Variable 'hum' is accessed from within inner class, needs to be final or effectively final

        result = predicate.test(other);
        consumer.accept(hum);

        /*
        Reference to an instance method of an arbitrary object of a particular type
        ContainingType::methodName
        String::compareToIgnoreCase , String::concat
        */
        predicate = Human::takeVoid;
        consumer = Human::takeVoid;

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

        result = predicate.test(hum);
        consumer.accept(hum);


        Predicate_b_p1_p2<Human, Human> face2 = Human::takeHuman;
        face2 = new Predicate_b_p1_p2<Human, Human>() {
            @Override
            public boolean test(Human human, Human human2) {
                //!return human.takeVoid(human2);
                return human.takeHuman(human2);
            }
        };
        result = face2.test(hum, other);// return hum.takeHuman(other);

        ConstructRef<Human> cr = Human::new;
        Human last = cr.func("New");

    }
}
