package u4.generics.s0;

public class GenApp {
    public static void main(String [] args){
        Gen<Integer> box1 = new Gen<Integer>();//Gen<Integer> box1 = new Gen<>();
        Gen<Long> box2 = new Gen<Long>();
        Object object = box1;
    }
}
