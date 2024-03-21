package u4.generics.s3.references;

public class App {
    public static void main(String[] args) {
        boolean result = false;

        Human first = new Human("first");
        Human second = new Human("second");
        Utilz utilz = new Utilz();

        //different interfaces
        Consumer_v_p1<Human> consumer = null;
        Predicate_b_p1<Human> predicate1 = null;
        Predicate_b_p1_p2<Human, Human> predicate2 = null;

        //Reference to a static method
        consumer = Utilz::stakeHuman;
        consumer.accept(first);//Utilz.takeHuman(first)
        predicate1 = Utilz::stakeHuman;
        result = predicate1.test(first);//Utilz.takeHuman(first)
        predicate2 = Utilz::scompareHumans;
        predicate2.test(first, second);//Utilz.scompareHumans(first,second)

        //Reference to an instance method of a particular object
        consumer = first::compare;
        consumer.accept(second);//first.compare(second)
        predicate1 = first::compare;
        result = predicate1.test(second);//first.compare(second)
        predicate2 = utilz::compareHumans;
        result = predicate2.test(first, second);//utilz.compareHumans(first, second)

        //Reference to an instance method of an arbitrary object of a particular type
        consumer = Human::takeVoid;
        consumer.accept(first);//first.takeVoid()
        predicate1 = Human::takeVoid;
        result = predicate1.test(first);// first.takeVoid()
        predicate2 = Human::compare;
        result = predicate2.test(first, second);//first.compare(second)

        // Reference to a constructor  ClassName::new  HashSet::new
        ConstructRef<Human> cr = Human::new;
        Human human = cr.func("New");
        ConstructorArrRef<Human> car = Human[]::new;
        Human[] humans = car.method(10);

    }
}
