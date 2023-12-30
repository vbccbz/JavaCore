package u9.handler;

import java.lang.reflect.*;

public class JPAApp {
    public String handle(Class<?> classRef){
        String str = null;

        return str;
    }

    public static void main(String[] args) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(int.class.toString());
        System.out.println(stringBuilder.toString());
        Class<?> c1 = Object.class;
        Class<?> c2 = Object.class;
        boolean b1 = c1.equals(c1);
        boolean b2 = c1.equals(c2);

    }


}