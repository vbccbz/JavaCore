package u4.generics.s2.set;

import java.util.*;

public class TreeSetApp {
    public static void main(String[] args) {
//        Set<String> set = new HashSet<>(); // HashMap inside , при выдаче выдаёт в случайном порядке
//        Set<String> set = new LinkedHashSet<>(); // сохраняет порядок добавления
//        Set<String> set = new TreeSet<>(); // сортировка по значению

        List<String> strings = Arrays.asList("bbb", "zzz", "xxx");
        Set<String> set = new TreeSet<>(strings);

      // ???
//        public class TreeSet<E> extends AbstractSet<E>
//                implements NavigableSet<E>, Cloneable, java.io.Serializable
//        {

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
