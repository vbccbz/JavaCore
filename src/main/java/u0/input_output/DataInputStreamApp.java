package u0.input_output;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class DataInputStreamApp {
    public static void main(String[] args) throws FileNotFoundException {
        InputStream is = null;

        is = System.in;

        FileInputStream fis = new FileInputStream("input.txt");
        is = fis;

        DataInputStream dis = new DataInputStream(is);


    }
}
