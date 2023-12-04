package u9.Annotations;

@java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)// CLASS (???), SOURCE (e.g. @Override)
@java.lang.annotation.Target({java.lang.annotation.ElementType.TYPE, java.lang.annotation.ElementType.METHOD})
public @interface MyAnnotation {
    int strangeValue();
}
