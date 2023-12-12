package u3.trycatch;

class MyArraySizeException extends ArrayIndexOutOfBoundsException {
    MyArraySizeException() {
        super("MyArraySizeException message :D");
    }
}