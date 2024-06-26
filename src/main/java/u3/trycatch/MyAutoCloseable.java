package u3.trycatch;

import java.io.FileNotFoundException;

public class MyAutoCloseable implements AutoCloseable {
    String name;
    boolean bad_opening = false;
    boolean bad_closing = false;

    public MyAutoCloseable(String name, boolean bad_opening, boolean bad_closing) throws Exception {
        this.name = name;
        this.bad_opening = bad_opening;
        this.bad_closing = bad_closing;
        if (bad_opening){
            System.out.println(name + " bad opening...");
            throw new Exception(name + " bad opening");
        }
        System.out.println(name + " normal opening");
    }

    @Override
    public void close() throws Exception {
        if (bad_closing){
            System.out.println(name + " bad closing...");
            throw new Exception(name + " bad closing");
        }
        System.out.println(name + " normal closing");
    }
}
