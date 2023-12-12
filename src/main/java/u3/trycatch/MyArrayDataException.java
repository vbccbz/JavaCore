package u3.trycatch;

class MyArrayDataException extends NumberFormatException {
    MyArrayDataException(int i, int j, String str) {
        super (i + " " + j + " " + str);
    }
}