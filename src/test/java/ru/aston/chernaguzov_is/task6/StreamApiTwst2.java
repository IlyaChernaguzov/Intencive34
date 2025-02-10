package ru.aston.chernaguzov_is.task6;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamApiTwst2 {
    private static final Person PERSON = new Person("Ivan", "Ivanov", 20, new Address(City.GOMEL, "Ilicha", "51"));
    private static Stream<Person> s;
    private static Stream<Person> s1;

    @BeforeEach
    void inti() {
        s = Stream.of(
                new Person("Ivan", "Ivanov", 20, new Address(City.GOMEL, "Ilicha", "51")),
                new Person("Sergey", "Sergeev", 30, new Address(City.MINSK, "Mira", "11")),
                new Person("Denis", "Denisov", 40, new Address(City.BREST, "Drugbu", "44")),
                new Person("Alex", "Alexandrov", 50, new Address(City.GRODNO, "Minskaya", "1")),
                new Person("Sveta", "Svetlova", 60, new Address(City.VITEBSK, "pr. Pobedu", "87")),
                new Person("Tanya", "Tatyanova", 60, new Address(City.MOGILEV, "Ilicha", "111"))
        );
        s1 = Stream.of(
                new Person("Ivan", "Ivanov", 20, new Address(City.GOMEL, "Ilicha", "51")),
                new Person("Sergey", "Sergeev", 30, new Address(City.MINSK, "Mira", "11")),
                new Person("Denis", "Denisov", 40, new Address(City.BREST, "Drugbu", "44")),
                new Person("Alex", "Alexandrov", 50, new Address(City.GRODNO, "Minskaya", "1")),
                new Person("Sveta", "Svetlova", 60, new Address(City.VITEBSK, "pr. Pobedu", "87")),
                new Person("Tanya", "Tatyanova", 60, new Address(City.MOGILEV, "Ilicha", "111"))
        );
    }

    @Test
    void filter() throws Exception {
        Assertions.assertEquals(PERSON, s
                .filter(p -> City.GOMEL == p.getAddress().getCity())
                .findFirst()
                .orElseThrow(() -> new Exception("no data")));
    }

    @Test
    @DisplayName("Найти возраст 1ого человека в списке")
    void map() throws Exception {

        Assertions.assertEquals(PERSON.getAge(), s
                .map(Person::getAge)
                .findFirst()
                .orElseThrow(() -> new Exception("no data")));
    }

    @Test
    @DisplayName("Из 2 стримов (списков) получить все адреса юзеров")
    void flatMap() throws Exception {

        Stream<Stream<Person>> s2 = Stream.of(s, s1);
        s2
                .flatMap(personStream -> personStream.map(p -> p.getAddress()))
                .forEach(System.out::println);
    }

    @Test
    @DisplayName("iterateJava8, сгенерировать последовательность 10 элем. с прирощением+1 начиная с 0")
    void iterateJava8() {
        Stream
                .iterate(0, i -> ++i)
                .limit(10)
                .forEach(System.out::println);
    }

    @Test
    @DisplayName("iterateJava9, сгенерировать последовательность 10 элем. с прирощением+1 начиная с 0")
    void iterateJava9() {
        Stream
                .iterate(0, i -> i <= 10, i -> ++i)
                .forEach(System.out::println);
    }

    @Test
    @DisplayName("найти max в сгененрированной последовательности")
    void max() {
        Integer max = Stream
                .iterate(0, i -> i <= 10, i -> ++i)
                .max(Integer::compareTo)
                .get();
        Assertions.assertEquals(10, max);
    }

    @Test
    @DisplayName("найти min в сгененрированной последовательности")
    void min() {
        Integer min = Stream
                .iterate(0, i -> i <= 10, i -> ++i)
                .min(Integer::compareTo)
                .get();
        Assertions.assertEquals(0, min);
    }

    @Test
    @DisplayName("посчитать count в сгененрированной последовательности")
    void count() {
        long count = Stream
                .iterate(0, i -> i <= 10, i -> ++i)
                .count();
        Assertions.assertEquals(11, count);
    }

    @Test
    @DisplayName("reduce - сложить все числа друг с другом в потоке")
    void reduce() {
        Integer reduce = Stream
                .iterate(0, i -> i <= 3, i -> ++i)
                .reduce(Integer::sum)
                .get();
        Assertions.assertEquals(6, reduce);
    }

    @Test
    @DisplayName("findAny - найти любой элемент")
    void findAny() {
        Integer findAny = Stream
                .iterate(0, i -> i <= 3, i -> ++i)
                .findAny()
                .get();
        Assertions.assertTrue(findAny instanceof Integer, "findAny=" + findAny);
    }

    @Test
    @DisplayName("sorted в обратном порядке")
    void sorted() {
        Stream<Integer> sorted = Stream
                .iterate(0, i -> i <= 3, i -> ++i)
                .sorted((o1, o2) -> {
                    if (Objects.equals(o1, o2)) return 0;
                    if (o1 > o2) return -1;
                    return 1;
                });

        Assertions.assertEquals(Stream.of(3, 2, 1, 0).collect(Collectors.toList()), sorted.collect(Collectors.toList()));
    }

    @Test
    @DisplayName("получить последовательность чисел в виде строки")
    void reduce1() {
        String start = Stream
                .iterate(0, i -> i <= 3, i -> ++i)
                .map(Integer::toUnsignedString)
                .reduce("start", (a, b) ->
                        a + b);
        Assertions.assertEquals("start0123", start);
    }

    @Test
    @DisplayName("найти четные нечетные числа в стриме за 1 раз")
    void partitioning() {
        List<Integer> numbers = Arrays.asList(-1, 2, -3, 4, -5);

        Map<Boolean, List<Integer>> partition = numbers.stream()
                .collect(Collectors.partitioningBy(n -> n < 0,
                        Collectors.mapping(v -> v, Collectors.toList())));

        System.out.println("Положительные числа: " + partition.get(false));
        System.out.println("Отрицательные числа: " + partition.get(true));
    }

    @Test
    @DisplayName("получить данные из 2 источников и вывести клиентов по городам (сгруппировать)")
    void groupingBy() {
        Stream
                .of(s, s1)
                .flatMap(personStream -> personStream)
                .distinct()
                .collect(Collectors.groupingBy(person -> person.getAddress()))
                .forEach((address, people) -> System.out.println(address + ":" + people));
    }

    @Test
    @DisplayName("преобразовать char в инт и найти кол-во элементов в которых стартовая последовательность <3")
    void transform() {
        long count = Stream
                .of("1", "2", "3")
                .mapToInt(Integer::valueOf)
                .takeWhile(value -> value < 3)
                .count();
        Assertions.assertEquals(2, count);
    }

    @Test
    @DisplayName("сгенерировать мильен объектов")
    void generate() {
        final List<Person> collect = Stream
                .of(s, s1)
                .flatMap(personStream -> personStream)
                .collect(Collectors.toList());
        Stream
                .generate(() -> {
                    Collections.shuffle(collect);

                    return collect.get(0);
                })
                .limit(10000)
                .forEach(System.out::println);
    }

    @Test
    void optionalExample() {
        Optional<Person> optionalPerson = Optional.of(PERSON);
        Optional<Person> optionalPerson2 = Optional.ofNullable(null);

        Assertions.assertTrue(optionalPerson.isPresent());
        Assertions.assertTrue(optionalPerson2.isEmpty());

        Optional<Address> address = optionalPerson.map(Person::getAddress);
//
        Assertions.assertEquals(PERSON.getAddress(), address.get());
//
        Assertions.assertEquals("Person not found", mapOptional(optionalPerson2));
//
//
        optionalPerson2
                .map(Person::getAge)
                .ifPresentOrElse(
                        age -> System.out.println("Age: " + age),
                        () -> System.out.println("Person not found")
                );
    }

    private String mapOptional(Optional<Person> optionalPerson) {
        return optionalPerson
                .map(Person::getName)
                .orElse("Person not found");
    }
}
