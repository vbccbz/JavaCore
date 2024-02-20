package u4.generics.s3.references;

public class Human {
    public String name;

    public Human(String name) {
        this.name = name;
    }

    public boolean takeVoid() {
        System.out.println(this.name);
        return true;
    }

    public boolean compare(Human human){
        System.out.println(this.name + " " + human.name );
        return true;
    }

    public boolean compareHumans(Human human, Human human2){
        System.out.println(this.name + " "+ human.name + " " + human2.name );
        return true;
    }
}
