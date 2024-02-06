package u2.association;

import java.util.*;

/*
A composition (also "composite aggregation" in the UML spec) is a special form of aggregation, where a component instance is part of at most one aggregate instance at a time (that is, it cannot be shared between several aggregates).

one-to-one, one-to-many, many-to-one, and many-to-many.

An association defines a relationship between two entity objects based on common attributes.

The relationship can be one-to-one or one-to-many; you can use two one-to-many associations to implement a many-to-many relationship.

The association allows entity objects to access the data of other entity objects through a persistent reference.

Bank can have many employees, So it is a one-to-many relationship.
-----> Employ
|
Bank ---------> Employ
|
-----> Employ

An aggregation is a special form of association with the intended meaning of a part-whole-relationship, but without a precise semantics.
 */
/*
has a relationship
 */
public class Vehicle {
    private Engine engine;
    private List<Person> crew;

    public Vehicle() {
        engine = new Engine();
    }

    public void getIn(Person person) {
        crew.add(person);
    }

    public void getOut(Person person) {
        crew.remove(person);
    }
}
