package u4.generics.s3.references;

public class Human {
    public String name;

    public Human(String name) {
        this.name = name;
    }

    public boolean takeVoid() {
        return true;
    }

    public boolean compare(Human human) {
        return true;
    }

    public boolean compare2(Human human, Human h2) {
        return true;
    }
}
