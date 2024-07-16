package u9.reflection.handler;


import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JPAApp {

    public static String handle(Object o) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Field f : o.getClass().getFields()) {
            Class<?> classref = f.getType();
            if (classref == int.class) {
                stringBuilder.append("int ");
            }
            if (classref == long.class) {
                stringBuilder.append("long ");
            }
            if (classref == String.class) {
                stringBuilder.append("String ");
            }
            if (classref == Boolean.class) {
                stringBuilder.append("Boolean ");
            }
            if (classref == Integer.class) {
                stringBuilder.append("Integer ");
            }
        }
        return stringBuilder.toString();
    }

    public static String handle2(Object o) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Field f : o.getClass().getFields()) {
            stringBuilder.append(f.getAnnotation(MyAnnotation.class).nameType() + " ");
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {

//        System.out.println(handle(new Student()));
//        System.out.println(handle2(new Student()));

        try {
            Class.forName("org.sqlite.JDBC");
            try (Connection connection = DriverManager.getConnection("jdbc:sqlite:main.db")) {
//                try(PreparedStatement preparedStatement = connection.prepareStatement("CREATE TABLE ?(name TEXT);")){
//
//                } catch (SQLException exception) {
//                    exception.printStackTrace();
//                }

            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        } catch (ClassNotFoundException exception) {
            exception.printStackTrace();
        }

    }


}