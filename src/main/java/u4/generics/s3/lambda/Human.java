package u4.generics.s3.lambda;

public class Human {
    public String name;

    public Human(String name) {
        this.name = name;
    }

    public boolean takeVoid() {
        System.out.println(this.name);
        return false;
    }

    public boolean takeHuman(Human h){
        System.out.println(this.name + " " + h.name );
        return true;
    }
}
