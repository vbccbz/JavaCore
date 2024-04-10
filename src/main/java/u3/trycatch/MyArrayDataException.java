package u3.trycatch;

class MyArrayDataException extends NumberFormatException {
    MyArrayDataException(int row, int column, String str) {
        super ("row: " + row + " " + "column: " + column + " " + "data: " + str);
    }
}