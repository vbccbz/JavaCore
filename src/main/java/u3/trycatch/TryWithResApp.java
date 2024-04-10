package u3.trycatch;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class TryWithResApp {
    public static void main(String[] args) {
       try (InputStream in3 = new FileInputStream("input1.txt");
            InputStream in4 = new FileInputStream("input2.txt")) {
           //...
       } catch (IOException exception) {
           exception.printStackTrace();
//        } catch (FileNotFoundException exception) {
//            exception.printStackTrace();
       }
    }
}
