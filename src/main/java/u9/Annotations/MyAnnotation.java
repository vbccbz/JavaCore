package u9.Annotations;

import java.lang.annotation.ElementType;

@java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
//@Retention(RetentionPolicy.CLASS)
//@Retention(RetentionPolicy.SOURCE) as @Override
@java.lang.annotation.Target({ElementType.TYPE, ElementType.METHOD})
public @interface MyAnnotation {
    int strangeValue();
}
