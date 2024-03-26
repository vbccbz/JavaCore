package u4.generics.s3.references;

public class App {
    public static void main(String[] args) {
        boolean result = false;

        Human first = new Human("first");
        Human second = new Human("second");
        Utilz<Human> utilz = new Utilz<>();

        //different interfaces
        VP<Human> consumer = null;
        BP<Human> predicate1 = null;
        BPP<Human, Human> predicate2 = null;

        //Reference to a static method
        consumer = Utilz::stake;
        consumer.accept(first);//Utilz.stake(first)
        predicate1 = Utilz::stake;
        result = predicate1.test(first);//Utilz.stake(first)
        predicate2 = Utilz::scompare;
        predicate2.test(first, second);//Utilz.scompare(first,second)

        //Reference to an instance method of a particular object
        consumer = first::compare;
        consumer.accept(second);//first.compare(second)
        predicate1 = first::compare;
        result = predicate1.test(second);//first.compare(second)
        predicate2 = utilz::compare;
        result = predicate2.test(first, second);//utilz.compare(first, second)

        //Reference to an instance method of an arbitrary object of a particular type
        consumer = Human::takeVoid;
        consumer.accept(first);//first.takeVoid()
        predicate1 = Human::takeVoid;
        result = predicate1.test(first);// first.takeVoid()
        predicate2 = Human::compare;
        result = predicate2.test(first, second);//first.compare(second)

        // Reference to a constructor
        ConstructRef<Human> cr = Human::new;
        Human human = cr.func("New");
        ConstructorArrRef<Human> car = Human[]::new;
        Human[] humans = car.method(10);

    }
}
