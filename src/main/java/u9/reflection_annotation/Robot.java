package u9.reflection_annotation;

@MyAnnotation(strangeValue = 1)
class Robot {
    static {
        System.out.println("I'm static initializer of Robot");
    }

    @MyAnnotation(strangeValue = 2)
    public int id;

    @MyAnnotation(strangeValue = 3)
    public Robot() {
    }

    @MyAnnotation(strangeValue = 4)
    public void set(int i) {
        id = i;
    }
}
