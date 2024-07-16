package u9.reflection;

class Robot {
    static {
        System.out.println("I'm static initializer of Robot");
    }

    public int id;

    public Robot() {
    }

    public void set(int i) {
        id = i;
    }
}
