package u9.Reflection.Refl;

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

import java.lang.reflect.*;
import java.net.*;
import java.io.*;

public class ReflectionApp {
    public static void main(String[] args) throws NoSuchMethodException, MalformedURLException, ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {

        java.lang.Object object = null;

        java.lang.Class<?> classR = null;

        java.lang.reflect.Constructor<?> constructor = null;
        java.lang.reflect.Field field = null;
        java.lang.reflect.Method method = null;

        classR = Robot.class;// works here, but better is u9.Reflection.Refl.Robot.class;
        classR = java.lang.Class.forName("u9.Reflection.Refl.Robot");
        /*
        only forName() runs only static initializer
         */

        constructor = classR.getConstructor();// ??? void.class isn't need ???
        object = constructor.newInstance();// static block is run here
        constructor = classR.getConstructor(int.class);
        object = constructor.newInstance(123);

        if (Modifier.isPublic(classR.getModifiers())) {
            System.out.println("public " + classR.getName());
        }

        field = classR.getField("id");// Field[] fields = classR.getFields();
        field.set(object, 123);
        System.out.println(field.getType() + " " + field.getName() + " " + field.get(object));

        method = classR.getMethod("work", int.class);//ethod[] methods = classR.getMethods();
        method.invoke(object, 456);


        java.io.File file = null;
        java.net.URL url = null;
        java.lang.ClassLoader classLoader = null;

        // any .class can be load
        file = new File("C:\\Users\\User\\Downloads");
        url = file.toURI().toURL();//???  url = file.toURL();
        classLoader = new URLClassLoader(new URL[]{url});
        classR = classLoader.loadClass("com.Robot");

    }
}
