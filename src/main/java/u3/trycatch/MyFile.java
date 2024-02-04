package u3.trycatch;

import java.io.FileNotFoundException;

public class MyFile implements AutoCloseable {
    String flag;
    public MyFile(){

    }
    public MyFile(String str){
        flag = str;
    }

    @Override
    public void close() throws Exception {
        if (flag != null){
            throw new Exception(flag);
        }
    }
}
