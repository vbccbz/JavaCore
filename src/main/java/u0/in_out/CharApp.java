package u0.in_out;

public class CharApp {
    public static void main(String[] args) {

        System.out.println((int) 49);// 49
        System.out.println(String.valueOf((int) 49)); // 49

        System.out.println((char) 49);// 1
        System.out.println(String.valueOf((char) 49)); // 1

        System.out.println(Character.valueOf((char) 49));// 1
        System.out.println(Character.toString((char) 49));// 1

        System.out.println('\u0031');// 1
        System.out.println("\u0031");// 1
        System.out.printf("%b", true);
        System.out.printf("%s", "ok");
        char ch = 24433;

        double d = 1.1;
        float f = 2.2F;
        f=(float)d*2;
        java.lang.Math.max(1,2);

        String s1 = "hello";
        String s2 = "hell";
        s2+="o";

        System.out.println(compareStrings(s1, s2));
        System.out.println(compareStrings2(s1, s2));

        Integer a = 100;
        int c = 100;
        System.out.println("!!!" + (a==c) );
    }

    public static boolean compareStrings(String s1, String s2) {return s1==s2;}

    public static boolean compareStrings2(String s1, String s2) {
        return s1.equals(s2);
    }
}
