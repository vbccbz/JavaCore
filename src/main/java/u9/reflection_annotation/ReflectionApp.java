package u9.reflection_annotation;

//import java.lang.*;

/*

work with terminal

java.lang.String str; works without import.
import java.lang.String is just alias.
import java.lang.* exists by default

Person person = new Person();// needs Person.java
classR = mst.getClass();
classR = Person.class;

Person.java must be connected to javac via -sourcepath or -classpath or maven or etc.
javac works only with *.java
C:\Users\User\IdeaProjects\JavaTerminal\spaceDir>javac -sourcepath "C:\Users\User\IdeaProjects\JavaTerminal\personDir" C:\Users\User\IdeaProjects\JavaTerminal\mainDir\*.java C:\Users\User\IdeaProjects\JavaTerminal\mainDir\someAnotherFileThatShouldBeCompiled.java -d C:\bin

Person.class must be connected to java via -classpath or maven or etc.
java works only with .class files only with their pure names.
The loaded Main.class must be the last parameter.

C:\Users\User\IdeaProjects\JavaTerminal\spaceDir>java -classpath C:\Users\User\IdeaProjects\JavaTerminal\personDirC:\Users\User\IdeaProjects\JavaTerminal\mainDir Main

classR = Class.forName("Person");// needs Person.class

C:\Users\User\IdeaProjects\JavaTerminal\spaceDir>java -classpath C:\Users\User\IdeaProjects\JavaTerminal\personDir;C:\Users\User\IdeaProjects\JavaTerminal\mainDir Main

IDEA ---> project with main() ---> config ---> create .jar ----> import .jar in the project libraries

C:\Users\User\IdeaProjects\JavaTerminal\personDir> jar cf p.jar .\Person.java
move p.jar to C:\

Doesn't work in gitbash
C:\Users\User\IdeaProjects\JavaTerminal\mainDir>java -classpath .;C:\Users\User\IdeaProjects\JavaTerminal\p.jar Main
ok
Person static block


C:\Users\User\IdeaProjects\JavaTerminal>javac -classpath ".\p.jar" .\mainDir\Main.java
C:\Users\User\IdeaProjects\JavaTerminal>java -classpath ".\mainDir;.\p.jar" Main

C:\Users\User\IdeaProjects\JavaTerminal>jar cf p.jar .\personDir\Person.java
*/

import java.lang.*;
import java.lang.Object;
import java.lang.Class;
import java.lang.reflect.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import  java.io.File;
import java.net.*;
import java.net.URL;
import java.lang.annotation.Annotation;


public class ReflectionApp {
    public static void main(String[] args) throws NoSuchMethodException, MalformedURLException, ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {

        Object object = null;
        Class<?> classObject = null;
        Constructor<?> constructor = null;
        Field field = null;
        Method method = null;

        //class loading, then class initialization ???

        //1 way
        classObject = java.lang.Class.forName("u9.reflection_annotation.Robot");// ONLY forName() runs static initializer !!!
        //2 way
        classObject = Robot.class;// works here
        classObject = Robot.class;// works everywhere
        //3 way
        File file = new File("C:\\Users\\User\\JavaCore\\src\\main\\java\\"); // any .class can be load
        URL url = file.toURI().toURL();// file.toURL(); is deprecated
        ClassLoader classLoader = new URLClassLoader(new URL[]{url});// doesn't work in maven exec:java // Failed to execute goal org.codehaus.mojo:exec-maven-plugin:1.2.1:exec (default-cli) on project JavaCore: The parameters 'executable' for goal org.codehaus.mojo:exec-maven-plugin:1.2.1:exec are missing or invalid
        classObject = classLoader.loadClass("u9.reflection_annotation.Robot");
        //4 way
        Robot robot = new Robot();
        classObject = robot.getClass();
        System.out.println("classObject: " + classObject);//class u9.reflection_annotation.Robot
        System.out.println("classObject.getName(): " + classObject.getName());// u9.reflection_annotation.Robot

        int modifiers = classObject.getModifiers();
        if (Modifier.isPublic(modifiers)) {
            System.out.println("It is a public class!");
        } else {
            System.out.println("It is a not public class!");
        }

        constructor = classObject.getConstructor();//void.class isn't need

        object = constructor.newInstance();// static initializer is run here if it is not "forName()" case

        field = classObject.getField("id");// Field[] fields = classObject.getFields();
        System.out.println("field.getName(): " + field.getName());
        System.out.println("field.getType().getName(): " + field.getType().getName());
        field.set(object, 123);
        System.out.println("field.get(object): " + field.get(object));

        method = classObject.getMethod("set", int.class);//Method[] methods = classObject.getMethods();
        method.invoke(object, 456);

        for( Annotation annotation : classObject.getAnnotations() ){
            System.out.println(annotation);
        }
        System.out.println();
        for( Annotation annotation : field.getAnnotations() ){
            System.out.println(annotation);
        }
        System.out.println();
        for( Annotation annotation : constructor.getAnnotations() ){
            System.out.println(annotation);
        }
        System.out.println();
        for( Annotation annotation : method.getAnnotations() ){
            System.out.println(annotation);
        }
        System.out.println();


        MyAnnotation myAnnotation = method.getAnnotation(MyAnnotation.class);


    }
}
