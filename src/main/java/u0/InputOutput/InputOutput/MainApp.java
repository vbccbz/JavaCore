package u0.InputOutput.InputOutput;

import java.io.*;

public class MainApp {
    public static String getString() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String s = br.readLine();
        return s;
    }

    public static char getChar() throws IOException {
        return getString().charAt(0);
    }

    public static int getInt() throws IOException {
        return Integer.parseInt(getString());// int ParseInt(String)
    }

    public static void some() throws IOException {
        {
            Object o1 = 123;
            System.out.println(o1.getClass());
            System.out.println(o1.getClass().getName());
            Object o2 = "hello";
            System.out.println(o2.getClass());
            System.out.println(o2.getClass().getName());
            Object o3 = new String("hello");
            System.out.println(o3.getClass());
            System.out.println(o3.getClass().getName());
        }
        {
            System.out.println("press Enter: ");
            int i;
            do {
                i = System.in.read();
                System.out.println(i);
            } while (i != '\n');

        }
        {
            System.out.println("Enter string and press Enter:");
            String str = getString();
            System.out.println(str);
            System.out.println("Enter char symbol and press Enter:");
            char c = getChar();
            System.out.println(c);
            System.out.println("Enter int value and press Enter:");
            int i = getInt();
            System.out.println(i);
        }

    }

    public static void main(String[] args) {

        String str = null;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));// в учебнике ни разу не помещался в try() Почему?
        try {
            FileWriter fw = new FileWriter("file.txt");// character stream to file
            while (true) {
                str = br.readLine();
                if (str.equals("stop")) {
                    break;
                }
                fw.write(str);
                fw.write('\n');
            }
            fw.close();

            BufferedReader br2 = new BufferedReader(new FileReader("file.txt"));
//        BufferedReader br2 = new BufferedReader( new InputStreamReader( new FileInputStream("file.txt")));
            str = br2.readLine();
            br2.close();

        } catch (IOException exc) {

        }
        PrintWriter pw = new PrintWriter(System.out, true);
        pw.println(str);
    }

}

