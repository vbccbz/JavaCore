package u3.TryCatch;

class MyArrayDataException extends NumberFormatException {
    MyArrayDataException(int i, int j, String str) {
        super (i + " " + j + " " + str);
    }
}