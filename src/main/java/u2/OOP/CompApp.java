package u2.OOP;

/**
 * A composition (also called "composite aggregation" in the UML spec) is a special form of aggregation,
 * where a component instance is part of at most one aggregate instance at a time (that is, it cannot be shared between several aggregates).
 */
class Engine {
}

class Car {
    private Engine engine;
    public Car(){
        engine = new Engine();
    }
}

class CompApp{
    public static void main(String[] args) {
        Car car = new Car();

    }
}