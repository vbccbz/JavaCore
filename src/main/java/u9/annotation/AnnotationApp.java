package u9.annotation;

import java.lang.annotation.Annotation;

public class AnnotationApp {
    public static void main(String[] args) {
        try {
            Class<?> classObject = Class.forName("u9.annotation.Robot");
            System.out.println(classObject.getName());
            for (Annotation annotation : classObject.getAnnotations()) {
                System.out.println(annotation);
            }
            System.out.println();
            for (var ctor : classObject.getConstructors()) {
                System.out.println(ctor);
                for (var annotation : ctor.getAnnotations()) {
                    System.out.println(annotation);
                }
                System.out.println();
            }
            System.out.println("Check presenting of annotation:");
            if (classObject.getConstructor().isAnnotationPresent(MyAnnotation.class)) {
                MyAnnotation annotation = classObject.getConstructor().getAnnotation(MyAnnotation.class);
                System.out.println(annotation);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
