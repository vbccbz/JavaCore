package u9.Reflection.Refl;

class Robot {
    static {
        System.out.println("I'm static initializer");
    }

    int id;
    Person person = new Person();// instance initializer

    public Robot() {
    }

    public Robot(int i) {
        id = i;
    }

    public void work() {
    }
}
