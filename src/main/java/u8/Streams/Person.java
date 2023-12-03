package u8.Streams;

public class Person {
    enum Position {
        ENGINEER, MANAGER, DIRECTOR;
    }

    private String name;
    private int age;
    private double salary;
    private Position position;

    public Person(String name, int age, Person.Position position, float salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getSalary() {
        return salary;
    }

    public Position getPosition() {
        return position;
    }

}
