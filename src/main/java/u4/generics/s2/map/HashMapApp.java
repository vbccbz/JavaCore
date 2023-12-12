package u4.generics.s2.map;

import java.util.*;

public class HashMapApp {

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; ++i) {
            map.put(nums[i], i);
        }

        int a = 0;
        int b = 0;
        for (int i = 0; i < nums.length; ++i) {
            a = i;
            int find = target - nums[i];
            if (map.containsKey(find)) {
                b = map.get(find);
                if (b == i) {
                    b = -1;
                } else {
                    break;
                }
            }

        }

        return new int[]{a, b};
    }

    public static void main(String[] args) {
        /*
        HashTable - устаревший.
        HashMap допускает null в key и в value.
        HashTable синхронизированная коллекция.
        HashMap отдаёт в виде Set, HashTable в виде Enum.
        */

        Map<String, Set<String>> map = new HashMap<>();// не гаранитирует порядок добавления
        // map = new LinkedHashMap<>();// сохраняет порядок добавления
        // map = new TreeMap<>();// сортирует по ключу, бинарное дерево?
        // map is'n collection

        map.put("Alex", new HashSet<String>(Arrays.asList("123", "456")));
        map.put("Bob", new HashSet<String>(Arrays.asList("16")));
        if (map.containsKey("Alex")) {
            map.get("Alex").add("777");
        }
        System.out.println(map);

        // keySet()
        Set<String> keySet = map.keySet();
        System.out.println(keySet);

        // values()
        Collection<Set<String>> sets = map.values();
        for (Set<String> set : sets) {
            System.out.print(set + ", ");
        }
        System.out.println();

        // HashMap hasn't iterator
        // entrySet()
        Set<Map.Entry<String, Set<String>>> entrySet = map.entrySet();
        System.out.println(entrySet);
        Iterator<Map.Entry<String, Set<String>>> iterator = entrySet.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Set<String>> entry = iterator.next();
//            System.out.print(entry + " , ");
            System.out.print(entry.getKey() + "=" + entry.getValue() + ", ");
        }
        System.out.println();

    }
}
