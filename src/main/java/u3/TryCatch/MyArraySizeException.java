package u3.TryCatch;

class MyArraySizeException extends ArrayIndexOutOfBoundsException {
    MyArraySizeException() {
        super("MyArraySizeException message :D");
    }
}