package u4.generics.s2.map;

import java.lang.*;
import java.util.*;

public class WordCountingApp {
    public static void wordCounting(String[] args) {
        String[] words = {"Java", "Cat", "Java", "Money", "Money", "Money"};
        Map<String, Integer> map = new HashMap<>(1000);

        for (String word : words) {

//            if (map.containsKey(word)) {
//                int i = map.get(word) + 1;
//                map.replace(word, i);
//            } else {
//                map.put(word, 1);
//            }
//
            map.put(word, map.getOrDefault(word, 0) + 1);

        }
        System.out.println(map);

        List<String> wordsList = new ArrayList<>(Arrays.asList(words));
        Set<String> unique = new HashSet<>(Arrays.asList(words));
        for (String word : unique) {
            System.out.print(word + "=" + Collections.frequency(wordsList, word) + ", ");
        }
        System.out.println();
    }

}
