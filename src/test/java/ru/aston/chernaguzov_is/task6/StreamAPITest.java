package ru.aston.chernaguzov_is.task6;

import org.junit.Test;
import ru.aston.chernaguzov_is.task4.model.User;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class StreamAPITest {

    @Test
    public void findElement (){
        //Найти первый четный элемент в списке [1, 2, 3, 4, 5]

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        Integer result = list.stream().filter(n -> n%2 == 0).findFirst().get();
        System.out.println(result);
    }

    @Test
    public void limit(){
        //Создайте Stream чисел от 2 до 10. Умножьте их на 2 и выведите результат на экран, ограничьтесь первыми десятью результатами.

        Stream<Integer> numbres = Stream.of(2, 3, 4, 5, 6, 7, 8, 9, 10);

        List<Integer> result = numbres.map(n -> n*2).limit(10).collect(toList());

        System.out.println(result);


    }

    @Test
    public void filterElement (){
        //Отфильтруйте объекты по определенному свойству, например, выведите все записи из базы данных, у которых значение поля равно 1.

        User user1 = new User(1L, "Ivanov", "Ivan", "9998797654", "ivan@test.ru", 2L);
        User user2 = new User(2L, "Sergeev", "Sergey", "9213456789", "sergey@test.ru", 1L);
        User user3 = new User(3L, "Nikolaev", "Nikolay", "9219876543", "nikolay@test.ru", 2L);
        User user4 = new User(4L, "Pertrov", "Petr", "9012346754", "petr@test.ru", 1L);

        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);

        List<User> result = users.stream().filter(u -> u.getOrderId() == 1).collect(Collectors.toList());
        System.out.println(result);

    }

    @Test
    public void intoMap(){
        //Собрать элементы Stream в карту, где ключом будет первая буква каждого слова, а значением — само слово. Отсортировать ключи в алфавитном порядке.

        Stream<String> words = Stream.of("Ivan", "Sergey", "Nikolay", "Petr");

       Map<String,String> result = words
                .collect(Collectors.toMap(
                        w -> w.substring(0, 1),
                        w -> w, (oldValue, newValue) -> newValue,
                        TreeMap::new
        ));

        System.out.println(result);
    }

    @Test
    public void getTwoStream(){
        //Создать два стрима: один из чисел от 0 до 10, другой из чисел от 10 до 20. Объединить их в один стрим и вывести на экран числа больше 10.

        Stream<Integer> s1 = Stream.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        Stream<Integer> s2 = Stream.of(10, 11, 12, 13, 14, 15, 16, 17, 18 ,19);

        Stream.of(s1, s2).flatMap(s -> s.filter(r -> r >10)).forEach(System.out::println);

    }

    @Test
    public void getGroupsByCity(){
        //Разделить пользователей в Stream на группы по городу (ближний и дальний план), посчитать количество пользователей в каждой группе.

        Stream<Person> people = Stream.of(
                new Person("Ivan", "Ivanov", 29, new Address(City.VITEBSK, "Gagarina", "7", 222)),
                new Person("Sergey", "Sergeev", 34, new Address(City.MINSK, "Lenina", "23", 0)),
                new Person("Nikolay", "Nikolaev", 25, new Address(City.MOGILEV, "Oktybrskay", "45", 181)),
                new Person("Petr", "Pertrov", 41, new Address(City.BREST, "Centralnay", "123", 328)),
                new Person("Petr", "Pertrov", 41, new Address(City.BREST, "Centralnay", "123", 328))
        );

        Map<Boolean, Long> result =
                people.collect(partitioningBy(p -> p.getAddress().getDistanceToTheCapital() < 200,
                        counting()));

//        Map<Boolean, Map<City, Long>> result =
//                people.collect(Collectors.partitioningBy(p -> p.getAddress().getDistanceToTheCapital() < 200,
//                        groupingBy(r -> r.getAddress().getCity(), counting())));

//        Map<Boolean,Map<City, String>> result =
//                people.collect(partitioningBy(p -> p.getAddress().getDistanceToTheCapital() < 200,
//                        groupingBy(p -> p.getAddress().getCity(),
//                                mapping(Person::getName,
//                                        joining(", ", "{","}")))));

//        Map<Boolean, List<City>> result =
//                people.collect(partitioningBy(p -> p.getAddress().getDistanceToTheCapital() < 200,
//                        Collectors.mapping(r -> r.getAddress().getCity(), Collectors.toList())));

//                Map<City, Map<String, List<Person>>> result =
//                people.collect(
//                        groupingBy(p -> p.getAddress().getCity(),
//                        groupingBy(Person::getName)));



//        System.out.println(result);


        System.out.println("дальний: " + result.get(false));
        System.out.println("ближний: " + result.get(true));

    }

    @Test
    public void getResidentsInCities(){
        //Сгруппировать пользователей в Stream по городу проживания, посчитать количество пользователей из каждого города и вывести результаты в виде карты мира с количеством пользователей из каждого города.
        Stream<Person> people = Stream.of(
                new Person("Ivan", "Ivanov", 29, new Address(City.VITEBSK, "Gagarina", "7", 222)),
                new Person("Sergey", "Sergeev", 34, new Address(City.MINSK, "Lenina", "23", 0)),
                new Person("Nikolay", "Nikolaev", 25, new Address(City.MOGILEV, "Oktybrskay", "45", 181)),
                new Person("Petr", "Pertrov", 41, new Address(City.BREST, "Centralnay", "123", 328))
        );

        Map<City, Long> result = people.collect(
                groupingBy(p -> p.getAddress().getCity(), counting()));

        System.out.println(result);
    }

    @Test
    public void getPairOfNumbers(){
        //Создание Stream пар чисел и поиск всех пар, у которых произведение равно заданному числу или которые удовлетворяют другому условию.
        final int NUMBER = 18;

        Stream<PairOfNumbers> pairOfNumbers = Stream.of(
                new PairOfNumbers(2, 9),
                new PairOfNumbers(3,6),
                new PairOfNumbers(2,5),
                new PairOfNumbers(1, 18),
                new PairOfNumbers(4,4)
        );


        List<PairOfNumbers> result =  pairOfNumbers
                .filter(p -> (p.getX()*p.getY()) == NUMBER).collect(Collectors.toList());

        System.out.println(result);

    }

    @Test
    public void findSurNameEmployee(){
        //Задано множество фамилий сотрудников. Разработать программу, которая отображает все фамилии, начинающиеся на букву «J». Задачу решить с использованием Stream API.
        Set<String> employees = new HashSet<>();
        employees.add("Jobs");
        employees.add("Ivanov");
        employees.add("Jeckman");
        employees.add("Jeckson");
        employees.add("Pertrov");


        Set<String> result = employees.stream().filter(r -> r.charAt(0) == 'J').collect(toSet());

        System.out.println(result);

    }

    @Test
    public void multiplicationOfNumbers (){
        //Соберите числа в Stream в одно число, перемножив их между собой и выведите результат.
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        int result = numbers.stream().reduce((a, b) -> a*b).get();

        System.out.println(result);
    }

    @Test
    public void divisionByThree (){
        //Сгруппировать числа в Stream по остатку от деления на 3, посчитать сумму чисел в каждой группе и вывести результаты.
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        Map<Boolean, Integer> result = numbers.stream().collect(Collectors.partitioningBy(n -> n%3 == 0,
                Collectors.summingInt(b -> b)));


        System.out.println("сумма чисел которые не делятся на 3 : " + result.get(false));
        System.out.println("сумма чисел которые делятся на 3: " + result.get(true));

    }

    @Test
    public void elementsInOneLine(){
        //Соберите все элементы Stream в одну строку через пробел и выведите результат.
        Stream<String> words = Stream.of("Ivan", "Sergey", "Nikolay", "Petr");

        String result = words.collect(Collectors.joining(" "));

        System.out.println(result);

    }


    @Test
    public void elementsInMap(){

        Stream<String> stringStream = Stream.of("apple", "banana", "cherry");
//        Map<String, String> groupedMap;
//        groupedMap = stringStream
//                .collect(Collectors.toMap(
//                        key -> key,
//                        value -> value,
//                        (o, o2) -> o,
//                        HashMap::new
//                ));
//        groupedMap.forEach((key, value) -> System.out.println(key + " " + value));

        Map<Character, List<String>> groupedMap = stringStream.collect(Collectors.groupingBy(s -> s.charAt(0)));
        System.out.println(groupedMap);

    }

}
