package u3.trycatch;

import java.io.FileNotFoundException;

public class MyAutoCloseable implements AutoCloseable {
    String name;
    String flag;

    public MyAutoCloseable(String name, String flag) throws Exception {
        this.name = name;
        this.flag = flag;
        if (flag.equals("bad open")){
            throw new Exception(name + " " + flag);
        }
        System.out.println(name + " " + "normal open...");

    }

    @Override
    public void close() throws Exception {
        if (flag.equals("bad close")){
            throw new Exception(name + " " + flag);
        }
        System.out.println(name + " " + "normal close...");
    }
}
