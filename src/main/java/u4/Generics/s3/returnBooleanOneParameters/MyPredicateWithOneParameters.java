package u4.Generics.s3.returnBooleanOneParameters;

@FunctionalInterface
public interface MyPredicateWithOneParameters<T> {
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