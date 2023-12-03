package u4.Generics.s2.GenericQueue;

public class GenQDemo {
    public static void main(String[] args) {
        Integer[] arr = new Integer[5];
        GenQueue<Integer> qi = new GenQueue<>(arr);

        for (int i = 0 ; i < 5 ; ++i){
            try {
                qi.put(i);
            } catch (QueueFullException e) {
                e.printStackTrace();
            }
        }
        try {
            qi.put(9);
        } catch (QueueFullException e) {
            e.printStackTrace();
//            System.out.println(e);
        }

        for (int i = 0 ; i < 5 ; ++i){
            try {
                System.out.println( qi.get());
            } catch (QueueEmptyException e) {
                e.printStackTrace();
            }
        }
    }
}
