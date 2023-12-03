package u4.Generics.s3.Lambda.returnVoid;

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
