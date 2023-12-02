package u2.OOP;

/**
 * one-to-one, one-to-many, many-to-one, and many-to-many.
 *
 * An association defines a relationship between two entity objects based on common attributes.
 * The relationship can be one-to-one or one-to-many; you can use two one-to-many associations to implement a many-to-many relationship.
 * The association allows entity objects to access the data of other entity objects through a persistent reference.
 *
 * Bank can have many employees, So it is a one-to-many relationship.
 * -----> Employ
 * |
 * Bank ---------> Employ
 * |
 * -----> Employ
 */

class Employ {
}

class Bank {
    private java.util.Set<Employ> employs;

    public void addEmployees(java.util.Set<Employ> employs) {
        this.employs = employs;
    }
}

class AssApp {
    public static void main(String[] args) {

        java.util.Set<Employ> employs = new java.util.HashSet<>();
        employs.add(new Employ());

        Bank bank = new Bank();
        bank.addEmployees(employs);

    }
}
