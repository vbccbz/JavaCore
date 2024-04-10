package u3.trycatch;

public class MyArrWithMyExc {
    String[][] data;

    MyArrWithMyExc(String[][] dd) {
        this.data = dd;
    }

    int get(int row, int column) throws MyArraySizeException, MyArrayDataException {
        if (data.length < row) {
            throw new MyArraySizeException();
        }
        if (data[row].length < column) {
            throw new MyArraySizeException();
        }

        int result = 0;
        try {
            result = Integer.valueOf(data[row][column]);
        } catch (NumberFormatException exception) {
            throw new MyArrayDataException(row, column, data[row][column]);
        }
        return result;
    }
}
