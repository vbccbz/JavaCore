package u2.association;

class AssApp {
    public static void main(String[] args) {
        Vehicle vehicle = new Vehicle();
        Person person = new Person();
        vehicle.getIn(person);
    }
}
