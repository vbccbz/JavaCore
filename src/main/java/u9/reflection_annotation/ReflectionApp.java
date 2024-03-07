package u9.reflection_annotation;

import java.lang.*;

/*
Unix:
User ~/CurrentPath
$ command

Win:
CurrentPath> command

Идея плохо определяет workind directory, javac и java норм.
C:\Users\User\JavaCore>C:\Users\User\ideaIU-2020.1.windows\bin\idea64.exe "C:\Users\User\JavaCore"
ok
C:\Users\User\JavaCore>C:\Users\User\ideaIU-2020.1.windows\bin\idea64.exe "."
open project with name '.'
idea64.exe works bad with "." argument as "current dir" and opens project with name '.'. The better solution is to add 
Windows Registry Editor Version 5.00

[HKEY_CLASSES_ROOT\Directory\Background\shell\Idea here\command]
@="\"C:\\Users\\User\\ideaIU-2020.1.windows\\bin\\idea64.exe\" %V"

.java может содержать сколько угодно классов default или private. 
javac компилирует public класс ТОЛЬКО если класс находится в одноимённом файле.

javac needs full path to .java
java need classpath and class name relative to classpath

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

javac [-sourcepath 'dir[;dir]...'] <path/*.java [path/*.java]...>

~/jt
$  javac -sourcepath './peoplesrc/;' ./appsrc/*.java -d 'spbin'   (but -classpath works here too...)
spbin/
    App.class
    people/
        Person.class
~/jt
$ java -classpath './spbin/;' App
ok
I am a Person

javac [-classpath '[dir][;dirs]...'] <path/*.java [path/*.java]...>

~/jt
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
import java.io.File;
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
        classObject = u9.reflection_annotation.Robot.class;// works everywhere
        //3 way
        classObject = new Robot().getClass();
        //4 way
        File file = new File("C:\\Users\\User\\JavaCore\\src\\main\\java\\"); // any .class can be load
        URL url = file.toURI().toURL();// file.toURL(); is deprecated
        ClassLoader classLoader = new URLClassLoader(new URL[]{url});// doesn't work in maven exec:java // Failed to execute goal org.codehaus.mojo:exec-maven-plugin:1.2.1:exec (default-cli) on project JavaCore: The parameters 'executable' for goal org.codehaus.mojo:exec-maven-plugin:1.2.1:exec are missing or invalid
        classObject = classLoader.loadClass("u9.reflection_annotation.Robot");

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
