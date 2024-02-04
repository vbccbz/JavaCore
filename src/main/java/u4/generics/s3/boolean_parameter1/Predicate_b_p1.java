package u4.generics.s3.boolean_parameter1;

@FunctionalInterface
public interface Predicate_b_p1<T> {
    boolean test(T t);
}

/*
Reference to a static method    ContainingClass::staticMethodName   Person::compareByAge
MethodReferencesExamples::appendStrings

Reference to an instance method of a particular object  containingObject::instanceMethodName    myComparisonProvider::compareByName
myApp::appendStrings2

Reference to an instance method of an arbitrary object of a particular type ContainingType::methodName  String::compareToIgnoreCase
String::concat

Reference to a constructor  ClassName::new  HashSet::new

*/