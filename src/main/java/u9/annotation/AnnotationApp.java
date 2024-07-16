package u9.annotation;

import java.lang.annotation.Annotation;

public class AnnotationApp {
    public static void main(String[] args) {
        try {
            Class<?> classObject = Class.forName("java.lang.String");
            for (Annotation annotation : classObject.getAnnotations()) {
                System.out.println(annotation);
            }
            for (var ctor : classObject.getConstructors()) {
                System.out.println(ctor);
                for (var annotation : ctor.getAnnotations()) {
                    System.out.println(annotation);
                }
                System.out.println();
            }
            if (classObject.getConstructor().isAnnotationPresent(MyAnnotation.class)) {
                MyAnnotation annotation = classObject.getConstructor().getAnnotation(MyAnnotation.class);
                System.out.println(annotation);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
