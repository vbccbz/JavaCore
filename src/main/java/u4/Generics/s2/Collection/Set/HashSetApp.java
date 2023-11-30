package u4.Generics.s2.Collection.Set;

import java.lang.*;
import java.util.*;

public class HashSetApp {
    public static void main(String[] args) {
        Set<String> set1 = new HashSet<>(); // HashMap inside , при выдаче выдаёт в случайном порядке
        Set<String> set2 = new HashSet<String>(set1);

        List<String> strings = Arrays.asList("bbb", "zzz", "xxx");
        Set<String> set = new TreeSet<>(strings);

        set.add("aaa");
        set.add("ccc");

//        for (int i = 0; i < list.size(); ++i) { // for i cannot work with any collections like Set

        Iterator<String> iterator = set.iterator();
        System.out.print(iterator.next() + " ");
        System.out.print(iterator.next() + '\n');

        for (iterator = set.iterator(); iterator.hasNext(); ) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();

        set.forEach((element) -> {
            System.out.print(element + " ");
        });
        System.out.println();

        for (iterator = set.iterator(); iterator.hasNext(); ) {
            String str = iterator.next();
            if (str.equals("zzz")) {
                iterator.remove();
            }
        }


    }
}
