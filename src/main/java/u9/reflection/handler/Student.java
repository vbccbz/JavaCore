package u9.reflection.handler;

public class Student {
    @MyAnnotation(nameType = "longKek")
    public long id;

    @MyAnnotation(nameType = "StringKek")
    public String name;

    @MyAnnotation(nameType = "intKek")
    public int score;

    @MyAnnotation(nameType = "IntegerKek")
    public Integer Score;
}
