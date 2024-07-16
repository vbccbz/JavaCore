package u9.reflection;

import java.lang.*;

//"import" doesn't mean "to import", it is just an alias. Linking occurs by -classpath.
import java.lang.Object;
import java.lang.Class;
import java.lang.reflect.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.io.File;
import java.net.*;
import java.net.URL;

public class ReflectionApp {
    public static void main(String[] args) throws NoSuchMethodException, MalformedURLException, ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {

        Object object = null;
        Class<?> classObject = null;
        Constructor<?> constructor = null;
        Field field = null;
        Method method = null;

        //class loading, then class initialization ???

        //1 way
        classObject = java.lang.Class.forName("u9.reflection.Robot");// ONLY forName() runs static initializer !!!
        //2 way
        classObject = Robot.class;// works here
        classObject = u9.reflection.Robot.class;// works everywhere
        //3 way
        classObject = new Robot().getClass();
        //4 way
        File file = new File("C:\\Users\\User\\JavaCore\\src\\main\\java\\"); // any .class can be load
        URL url = file.toURI().toURL();// file.toURL(); is deprecated
        ClassLoader classLoader = new URLClassLoader(new URL[]{url});// doesn't work in maven exec:java // Failed to execute goal org.codehaus.mojo:exec-maven-plugin:1.2.1:exec (default-cli) on project JavaCore: The parameters 'executable' for goal org.codehaus.mojo:exec-maven-plugin:1.2.1:exec are missing or invalid
        classObject = classLoader.loadClass("u9.reflection.Robot");

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



    }
}
