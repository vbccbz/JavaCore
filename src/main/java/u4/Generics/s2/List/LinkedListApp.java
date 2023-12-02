package u4.Generics.s2.List;

public class LinkedListApp {
    public static void main(String[] args) {

        Integer[] integers = new Integer[]{1, 2, 3};

        // allows to create Stack and Queue
        // double linked double ended
        java.util.List<Integer> list1 = null;
        java.util.List<Integer> list2 = null;
        list1 = new java.util.LinkedList<Integer>();
        list2 = new java.util.LinkedList<Integer>(list1);

        list1 = java.util.Arrays.asList(1, 2, 3);// fixed size list
        list2 = new java.util.ArrayList<Integer>(java.util.Arrays.asList(1,2,3));// to fix fixed size :-)
        list1 = java.util.Arrays.asList(Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(3));
        list1 = java.util.Arrays.asList(integers);

        list1 = java.util.List.of(1, 2, 3);
        list1 = java.util.List.of(integers);

        integers = list1.toArray(new Integer[list1.size()]);

        for (int i = 0; i < list2.size(); ++i) {
            Integer temp = list2.get(i);
            temp += 1;
            list2.set(i, temp);
        }
        for (Integer i : list1) { // for-each deny modifications
//            java android game doesn 't like creation of object due to GC. Instead you must use pool of objects.
//            for -iterator and for -each both are too bad for traversal of a collection.
//            The problem is in list1.iterator() - it is a creation of a new iterator !Every time.
//            for -i was preferred there.
            java.lang.System.out.println(i);
        }
        // ListIterator can set
        java.util.Iterator<Integer> iterator = list1.iterator();
        int sum = 0;
        while (iterator.hasNext()) {
            sum += iterator.next();
        }
        java.util.ListIterator<Integer> listIterator = list1.listIterator();
        while (listIterator.hasNext()) {
            int temp =listIterator.next();
            listIterator.set( temp * 10);
        }
        while (listIterator.hasPrevious()) {
            System.out.print(listIterator.previous() + " ");
        }
        System.out.println();

        list1.forEach((element) -> {
            System.out.println(element);
        });
        list1.sort((o1,o2)->{ return 0;});

        java.util.Collections.sort(list1);// Integer implements Comparable<Integer>
        java.util.Collections.sort(list1, (o1, o2) -> {
            return 0;
        });


    }
}
