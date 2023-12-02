package u2.OOP;

/**
 * An aggregation is a special form of association with the intended meaning of a part-whole-relationship, but without a precise semantics.
 */

class Lecture {
}

class Course {
    private java.util.Set<Lecture> lectures;

    public void addEmployees(java.util.Set<Lecture> lectures) {
        this.lectures = lectures;
    }
}

class AggApp {
    public static void main(String[] args) {
        Lecture l1 = new Lecture();
        Lecture l2 = new Lecture();
        Lecture l3 = new Lecture();

        java.util.Set<Lecture> lecturesA = new java.util.HashSet<>();
        lecturesA.add(l1);
        lecturesA.add(l2);

        Course courseA = new Course();
        courseA.addEmployees(lecturesA);

        java.util.Set<Lecture> lecturesB = new java.util.HashSet<>();
        lecturesB.add(l1);
        lecturesB.add(l3);

        Course courseB = new Course();
        courseB.addEmployees(lecturesA);
    }
}
