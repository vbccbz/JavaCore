package u3.trycatch;

/*

Throwable
    Error
    Exception
        RuntimeException
    IOException

 */
class MyExcDemo {
    String[][] data;

    MyExcDemo(String[][] dd) {
        this.data = dd;

        for (int a = 0; a < data.length; ++a) {
            for (int b = 0; b < data[a].length; ++b) {
                data[a][b] = "1";
            }
        }
        data[0][0] = "1lol2BadNumber";

    }

    void method() throws MyArraySizeException, MyArrayDataException {
        if (data.length != 4) {
            throw new MyArraySizeException();
        }
        for (int i = 0; i < data.length; ++i) {
            if (data[i].length != 4) {
                throw new MyArraySizeException();
            }
        }

        int sum = 0;
        for (int a = 0; a < data.length; ++a) {
            for (int b = 0; b < data[a].length; ++b) {
                String str = data[a][b];
                int context;
                try {
                    context = Integer.valueOf(str);
                } catch (Throwable t) {
                    throw new MyArrayDataException(a, b, data[a][b]);
                }
                sum += context;
            }
        }
        System.out.println("sum is " + sum);
    }

    public static void main(String[] args) {
        MyExcDemo a = new MyExcDemo(new String[4][4]);
//        App a = new App(new String[3][4]);
//        App a = new App(new String[4][3]);

        try {
            a.method();
        } catch (MyArraySizeException m) {
            System.out.println(m.getMessage());
        } catch (MyArrayDataException m) {
            System.out.println(m.getMessage());
        } finally {
            System.out.println("finally :D");
        }

    }


}
