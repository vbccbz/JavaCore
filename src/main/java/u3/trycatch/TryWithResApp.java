package u3.trycatch;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class TryWithResApp {
    public static void main(String[] args) {
//        InputStream in1 = null;
//        InputStream in2 = null;
//        try {
//            in1 = new FileInputStream("input1.txt");
//            in2 = new FileInputStream("input2.txt");
//            int data = 0;
//            try {
//                while ((data = in1.read()) != -1) {
//                    System.out.println(data);
//                }
//            } catch (IOException exception) {
//                exception.printStackTrace();
//            }
//        } catch (FileNotFoundException exception) {
//            exception.printStackTrace();
//        } finally {
//            if (in1 != null) {
//                try {
//                    in1.close();
//                } catch (IOException exception) {
//                    exception.printStackTrace();
//                }
//            }
//            if (in2 != null) {
//                try {
//                    in2.close();
//                } catch (IOException exception) {
//                    exception.printStackTrace();
//                }
//            }
//        }


//        try (InputStream in3 = new FileInputStream("input1.txt");
//             InputStream in4 = new FileInputStream("input2.txt")) {
//            //...
//        } catch (IOException exception) {
//            exception.printStackTrace();
////        } catch (FileNotFoundException exception) {
////            exception.printStackTrace();
//        }

        //suppressed exceptions
        try (MyFile badClose1 = new MyFile("first dead");
             MyFile badClose2 = new MyFile("second dead")) {
            System.out.println("try");
            throw new Exception("from try");
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            System.out.println("final");
        }
    }
}
