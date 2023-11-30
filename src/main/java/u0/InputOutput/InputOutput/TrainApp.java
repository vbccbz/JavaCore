package u0.InputOutput.InputOutput;

import java.io.*;

public class TrainApp {
    public static void main(String[] args) {
        try (InputStream inputStream = new FileInputStream("text.txt")) {
            int ch;
            ch = inputStream.read();
            System.out.println(ch);
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        try (OutputStream outputStream = new FileOutputStream("text.txt", true)) {
            outputStream.write('z');
//            outputStream.flush();
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }
}
