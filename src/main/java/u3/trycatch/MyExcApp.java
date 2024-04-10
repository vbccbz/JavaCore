package u3.trycatch;

class MyExcApp {

    public static void main(String[] args) {
        MyArrWithMyExc a = new MyArrWithMyExc(new String[][]{{"123", "456"}, {"7fdg77", "88"}});
        try {
            int data = 0;
            data = a.get(0, 0);
            data = a.get(1,0);
            data = a.get(10,15);
            System.out.println(data);
        } catch (MyArraySizeException m) {
            System.out.println(m.getMessage());
        } catch (MyArrayDataException m) {
            System.out.println(m.getMessage());
        } finally {
            System.out.println("finally :D");
        }

    }


}
