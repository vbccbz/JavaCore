package u11.decorator;

public abstract class Decorator implements FileData {
    FileData fileData;

    Decorator(FileData fileData) {
        this.fileData = fileData;
    }

    @Override
    public void write(byte b) {
        fileData.write(b);
    }

    @Override
    public byte read() {
        return fileData.read();
    }
}
