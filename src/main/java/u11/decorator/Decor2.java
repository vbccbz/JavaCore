package u11.decorator;

public class Decor2 extends Decorator {
    Decor2(FileData fileData) {
        super(fileData);
    }

    @Override
    public void write(byte b) {
        super.write(b);
    }

    @Override
    public byte read() {
        return super.read();
    }
}
