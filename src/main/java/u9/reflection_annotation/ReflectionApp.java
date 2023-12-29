package u9.reflection_annotation;

import java.lang.*;

/*

~/jt
$ ls
appsrc/
    App.java
peoplesrc/
    people/
        Person.java
peoplebin/
    people/
        Person.class

~/jt
javac -sourcepath '[dir][;dirs]...' [file-path.ext ]...
$  javac -sourcepath './peoplesrc/;' ./appsrc/*.java -d 'spbin'   (but -classpath works here too...)
spbin/
    App.class
    people/
        Person.class
~/jt
$ java -classpath './spbin/;' App
ok
I am a Person

~/jt
javac -classpath '[dir][;dirs]...' [file-path.ext ]...
$ javac -classpath './peoplebin/;' ./appsrc/*.java -d 'cpbin'
cpbin/
    App.class
~/jt
$ java -classpath './peoplebin/;./cpbin/;' App
ok
I am a Person


"C:\"
'C:\'
'C://'


"import" doesn't mean "to import", it is just an alias. Linking occurs by -classpath.

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
