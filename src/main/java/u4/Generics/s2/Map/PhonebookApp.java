package u4.Generics.s2.Map;

import java.util.*;

public class PhonebookApp {
    public Map<String, Set<String>> map;

    public PhonebookApp() {
        map = new HashMap<String, Set<String>>(1000);
    }

    public void addEntry(String name, String number) {
//        List <String> l = Arrays.asList("kek", "lol");
//        Set<String> s = new TreeSet<String>(l);

        if (map.containsKey(name)) {
            map.get(name).add(number);
        } else {
            map.put(name, new HashSet<String>(Arrays.asList(number)));
        }
    }

    public Set<String> getNumbers(String name) {
        System.out.print("Find " + name + "... ");
        return map.get(name);
    }

    public String getName(String number) {
        System.out.print("Find " + number + "... ");
        String name = "none";

        for (Map.Entry<String, Set<String>> entry : map.entrySet()) {
            if (entry.getValue().contains(number)) {
                return entry.getKey();
            }
        }

        return name;
    }

    public static void main(String[] args) {
        PhonebookApp phonebook = new PhonebookApp();
        phonebook.addEntry("George", "1234");
        phonebook.addEntry("George", "999");
        phonebook.addEntry("George", "111");
        phonebook.addEntry("Nord", "211");
        phonebook.addEntry("Lukas", "5623");
        phonebook.addEntry("Nord", "122");
        phonebook.addEntry("Arny", "25211");

        System.out.println(phonebook.map);
        System.out.println(phonebook.getNumbers("George"));
        System.out.println(phonebook.getName("122"));
    }
}
