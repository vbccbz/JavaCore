package u3.trycatch;

public class MySuppressedExceptionApp {
    public static void main(String[] args) {
        try (MyFile badClose1 = new MyFile("first", "bad close");
             MyFile badClose2 = new MyFile("second", "ok close")) {
            System.out.println("1");
            throw new Exception("2");
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            System.out.println("3");
        }
    }
}
