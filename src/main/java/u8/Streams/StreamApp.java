package u8.Streams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamApp {
    public static void main(String[] args) {
        Person[] persons = {
                new Person("Иван", 25, Person.Position.DIRECTOR, 60000f),
                new Person("Николай", 27, Person.Position.ENGINEER, 55000.25f),
                new Person("Олег", 29, Person.Position.ENGINEER, 87123.55f),
                new Person("Пётр", 34, Person.Position.ENGINEER, 33000.33f),
                new Person("John Smith", 42, Person.Position.ENGINEER, 102000.89f),
                new Person("Володя", 24, Person.Position.MANAGER, 37000.24f)
        };

        String[] str = {"ab", "два", "три", "четыре", "три", "один", "четыре", "четыре", "два", "три", "четыре", "ab"};

    }

    /*
    Stream: a b c d

    intermediate intermediate intermediate terminal
     a               A
     b               B
     c               C
     d               D
                                 A
                                 B
                                 C
                                 D
                                             A
                                             B
                                             C
                                             D

    intermediate intermediate intermediate
    ...           ...          ...

    intermediate intermediate terminal intermediate
    ERR

    stateless (each element independs of the others) / stateful

    Non-interfering means that the data source is not modified by the operation

    associative is used in arithmetic sense
    */
    public static void zeroEx() {
        Stream.of("a", "b", "c", "d")
                .filter((s) -> {
                    System.out.println("filter: " + s);
                    return true;
                })
                .map((s) -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .sorted((s1, s2) -> {
                    System.out.println("sorted: " + s1 + " " + s2);
                    return 0;
                })
                .forEach((s) -> {
                    System.out.println("forEach: " + s);
                });
    }

    public static void firstEx(Person[] arr) {
        List<Person> persons = new ArrayList<>(Arrays.asList(arr));

        List<Person> engineers = new ArrayList<>();
        for (Person o : persons) {
            if (o.getPosition() == Person.Position.ENGINEER) {
                engineers.add(o);
            }
        }
        engineers.sort(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getAge() - o2.getAge();
            }
        });
        List<String> engineersNames = new ArrayList<>();
        for (Person o : engineers) {
            engineersNames.add(o.getName());
        }
        System.out.println(engineersNames);

        List<String> engineersNamesShortPath = persons.stream()
                .filter(person -> person.getPosition() == Person.Position.ENGINEER)
                .sorted((o1, o2) -> o1.getAge() - o2.getAge())
                .map((Function<Person, String>) person -> person.getName())
                .collect(Collectors.toList());
        System.out.println(engineersNamesShortPath);
    }

    public static void simpleStringEx() {
        System.out.println(Arrays
                .stream(
                        "A B CC B C AA A A B CC C".split("\\s")
                )
                .distinct()
                .count()
        );
    }

    public static void fileEx(String[] args) {
        try {
            Files.lines(Paths.get("text.txt"))
                    .map(line -> line.split("\\s"))
                    .distinct()
                    .forEach(arr -> System.out.println(Arrays.toString(arr)));
            System.out.println("----------------------");
            Files.lines(Paths.get("text.txt"))
                    .map(line -> line.split("\\s")) // arr[0] arr[1] arr[2] arr[3]
                    .map(Arrays::stream)
                    .distinct()
                    .forEach(System.out::println);
            System.out.println("----------------------");
            System.out.println(Files.lines(Paths.get("text.txt"))
                    .map(line -> line.split("\\s")) // arr[0] arr[1] arr[2] arr[3]
                    .flatMap(Arrays::stream)
                    /*
                     *   applies Arrays::stream to each [] ---> Stream Stream Stream
                     *
                     *
                     */
                    .distinct()
                    .collect(Collectors.joining(", ", "Уникальные слова: ", ".")));
            System.out.println("----------------------");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void streamFromFilesEx() {
        try {
            Files.lines(Paths.get("123.txt")).map(String::length).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void streamCreationEx() {
        Arrays.asList("A", "B", "C").stream().forEach(System.out::println);
        Stream.of(1, 2, 3, 4).forEach(System.out::println);
        Arrays.stream(new int[]{4, 3, 2, 1}).forEach(System.out::println);
    }

    public static void intStreamsEx() {
        List<Integer> list = Arrays.asList(1, 2, 3);
        IntStream myIntStream = IntStream.of();
        System.out.println(list.stream().mapToInt(v -> v).sum());

        IntStream.rangeClosed(2, 10).filter(n -> n % 2 == 0).forEach(System.out::println);
    }

    public static void reduceEx() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        int sum = 0;
        for (Integer o : list) {
            sum += o;
        }

        int streamSum = list.stream().reduce(0, (a, b) -> a + b);
        System.out.println(sum + " " + streamSum);
    }

    public static void reduce2() {
        System.out.println(Stream.of(1, 2, 3).reduce((s1, s2) -> s1 > s2 ? s1 : s2).get());
        System.out.println(Stream.of(1, 2, 3).reduce(0, (s1, s2) -> s1 > s2 ? s1 : s2));

        System.out.println(Stream.of(1, 2, 3).reduce((s1, s2) -> s1 + s2).get());
        System.out.println(Stream.of(1, 2, 3).reduce(0, (s1, s2) -> s1 + s2));

        System.out.println(Stream.of("a", "b").map((s) -> 1L).reduce(0L, (s1, s2) -> s1 + s2));
        System.out.println(Stream.of("a", "b").mapToLong((s) -> 1L).sum());
        System.out.println(Stream.of("a", "b").count());

        System.out.println(Stream.of(1, 2, 3, 4).map((n) -> 1).reduce(0, (s1, s2) -> s1 + s2));
        System.out.println(Stream.of(1, 2, 3, 4).mapToLong((n) -> 1).sum());
        System.out.println(Stream.of(1, 2, 3, 4).count());
//        System.out.println(  Stream.of(1, 2, 3, 4).reduce( (n)-> { return mapToLong((n) -> 1) } ).sum());
    }

    public static void parallel_reduceEx() { // ???
        System.out.println(
                Stream.of(2, 3, 4).sequential().reduce(0, (a, b) -> {
                    System.out.println("accumulator: " + a + " " + b);
                    return b;
                }, (a, b) -> {
                    System.out.println("combiner: " + a + " " + b);
                    return a + b;
                })
        );

        System.out.println("\n\n\n");
        System.out.println(
                Stream.of(2, 3, 4).parallel().reduce(0, (a, b) -> {
                    System.out.println("accumulator: " + a + " " + b);
                    return b;
                }, (a, b) -> {
                    System.out.println("combiner: " + a + " " + b);
                    return a + b;
                })
        );

        System.out.println("\n\n\n");
        System.out.println(
                Stream.of(2, 3, 4).sequential().reduce(0, (a, b) -> {
                    System.out.println("accumulator: " + a + " " + b);
                    return a + b;
                }, (a, b) -> {
                    System.out.println("combiner: " + a + " " + b);
                    return a + b;
                })
        );

        System.out.println("\n\n\n");
        System.out.println(
                Stream.of(2, 3, 4).parallel().reduce(0, (a, b) -> {
                    System.out.println("accumulator: " + a + " " + b);
                    return a + b;
                }, (a, b) -> {
                    System.out.println("combiner: " + a + " " + b);
                    return a + b;
                })
        );

    }

    public static void mappingEx() {
        Predicate<Integer> evenNumberFilter = n -> n % 2 == 0;
        Function<Integer, Integer> cube = n -> n * n * n;

        Stream.of(1, 2, 3).map(n -> Math.pow(n, 3));
        Stream.of(1, 2, 3).map(cube);

        List<String> list = Arrays.asList("A", "BB", "C", "DDD", "EE", "FFFF");
        // List<Integer> wordsLength = list.stream().map(str -> str.length()).collect(Collectors.toList());

        List<Integer> wordsLength = list.stream().map((s) -> s.length()).collect(Collectors.toList());
//        List<Integer> wordsLength = list.stream().map(String::length).collect(Collectors.toList());
        Function<String, Integer> _strToLen = String::length;
        Function<String, Integer> strToLen = s -> s.length();
//        List<Integer> wordsLength = list.stream().map(strToLen).collect(Collectors.toList());

        System.out.println(list);
        System.out.println(wordsLength);

        list.stream().map(strToLen).forEach(n -> System.out.println(n));
        list.stream().map(strToLen).forEach(System.out::println);
    }

    public static void findAnyEx() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        list.stream().filter(n -> n > 10).findAny().ifPresent(System.out::println);
        Optional<Integer> opt = list.stream().filter(n -> n > 10).findAny();
        if (opt.isPresent()) {
            System.out.println(opt.get());
        }
    }

    public static void matchEx() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        System.out.println(list.stream().allMatch(n -> n < 10));
        System.out.println(list.stream().anyMatch(n -> n == 4));
        System.out.println(list.stream().noneMatch(n -> n == 2));
    }

    public static void distinctEx() {
        // получаем поток данных из набора целых чисел, находим среди них только уникальные, и каждое
        // найденное значение выводим в консоль
        System.out.println("Первый вариант: ");
        Arrays.asList(1, 2, 3, 4, 4, 3, 2, 3, 2, 1).stream().distinct().forEach(n -> System.out.println(n));
        // делаем то же самое, что и в первом случае, только используем более короткую запись System.out::println
        System.out.println("Второй вариант: ");
        Arrays.asList(1, 2, 3, 4, 4, 3, 2, 3, 2, 1).stream().distinct().forEach(System.out::println);
    }

    public static void limitEx() {
        // Создаем список целых чисел
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        List<Integer> out = numbers.stream()   // преобразуем список целых чисел в поток данных и начинаем обработку
                .filter(n -> n % 2 == 0)       // фильтруем поток, оставляем в нем только четные числа
                .map(n -> n * n)               // преобразуем каждый элемент потока int -> int, умножая на 2
                .limit(2)                      // оставляем в потоке только 2 первых элемента
                .collect(Collectors.toList()); // собираем элементы потока в лист
        System.out.println(numbers);
        System.out.println(out);
    }

    public static void findAndSortWords(String[] array) {// LOL
        List<String> list = Arrays.stream(array)
                .sorted((o2, o1) -> (int) (
//                        Arrays.stream(array).filter(o1::equals).count() -
                                Arrays.stream(array).filter((str) -> {
                                    return o1.equals(str);
                                }).count() -
                                        Arrays.stream(array).filter(o2::equals).count())
                )
//                .distinct()
                .collect(Collectors.toList());
        System.out.println(list);
    }

    public static void findAndSortWords2(String[] array) {
        String str = Arrays.stream(array)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .max( //Comparator.comparingLong((e) -> e.getValue())
                        Comparator.comparingLong(Map.Entry<String, Long>::getValue))
                .get()
                .getKey();

//        Arrays.stream(array).collect(Collectors.groupingBy())
        System.out.println(str);
    }

    public static String findMostFrequentlyWord(String[] array) {
        List<String> uniques = Arrays.stream(array).distinct().collect(Collectors.toList());

        String maxWord = null;
        long max = 0;
        for (String unique : uniques) {
            long result = Stream.of(array).filter(Predicate.isEqual(unique)).count();
            if (result > max) {
                max = result;
                maxWord = unique;
            }
        }
        return (maxWord + " " + max);
    }

    public static double averageSalary(Person[] people) {
        return Stream.of(people).map(Person::getSalary).map(Double::valueOf).reduce(0.0, Double::sum) / people.length;
//        return Arrays.stream(people).mapToDouble(Person::getSalary).average().getAsDouble();
//        return Stream.of(people).mapToDouble(Person::getSalary).average().getAsDouble();
//        return Stream.of(people).collect(Collectors.averagingDouble(Person::getSalary));
    }

    public static String oldestN(Person[] people, int n) {
        if (n <= 0) {
            return "none";
        }
        return Arrays.stream(people).sorted((o1, o2) -> o2.getAge() - o1.getAge()).limit(n).map(Person::getName).collect(Collectors.joining(" "));
    }

    public static String oldestN2(Person[] persons, int n) {
        return Arrays.stream(persons).sorted((o1, o2) -> o2.getAge() - o1.getAge()).limit(n).map(Person::getName).collect(Collectors.joining(" "));
    }

    public static String oldestNMy(Person[] persons, int n) {

        String str = Stream.of(persons)
                .sorted( (a, b) -> {
                    if (a.getAge() > b.getAge()){
                        return -1;
                    }else if (a.getAge() < b.getAge() ){
                        return +1;
                    }else{
                        return 0;
                    }
                })
                .limit(n)
                .map(Person::getName)
                .reduce("", (a, b) -> a + b + " ");
        return "N самых старших сотрудников зовут:" + str;

//        return null;
    }
}
