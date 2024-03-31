package u11.decorator;

public class Decor1 extends Decorator {
    Decor1(FileData fileData) {
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
