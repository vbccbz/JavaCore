package u3.trycatch;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/*
Throwable
    Error (unchecked)
    Exception
        RuntimeException (unchecked)
        ... (checked)
*/

public class FullTryApp {
    public static void main(String[] args) {
        InputStream in1 = null;
        InputStream in2 = null;
        try {
            in1 = new FileInputStream("input1.txt");
            in2 = new FileInputStream("input2.txt");
            int data = 0;
            try {
                while ((data = in1.read()) != -1) {
                    System.out.println(data);
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        } finally {
            if (in1 != null) {
                try {
                    in1.close();
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
            if (in2 != null) {
                try {
                    in2.close();
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
        }

    }
}
