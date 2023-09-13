package ru.aston.chernaguzov_is.task1;


import org.junit.jupiter.api.Test;

import java.util.*;

import static java.util.Collections.*;
import static java.util.Optional.*;

import static org.junit.jupiter.api.Assertions.*;

public class CollectionTest {

    @Test
    public void arrayListTest (){
        ArrayList<String> list1 = new ArrayList<>();
        list1.add("Bob");
        list1.add("Tom");
        list1.add("Jack");
        assertEquals(3, list1.size());

        list1.add(0, "Clive");
        String nameAdd = list1.get(0);
        assertEquals("Clive", nameAdd);

        int indexAddLast = list1.indexOf("Clive");
        assertEquals(0, indexAddLast);

        boolean isEmpty = list1.isEmpty();
        assertFalse(isEmpty);

        boolean contains = list1.contains("Bob");
        assertTrue(contains);

        ArrayList<String> copyOfList1 = (ArrayList<String>) list1.clone();
        assertEquals(4, copyOfList1.size());

        list1.addAll(1, copyOfList1);
        assertEquals(8, list1.size());

        Iterator<String> iterator = list1.iterator();
        int index = 0;
        while (iterator.hasNext()){
            iterator.next();
            if(index == 1)
                iterator.remove();
            index++;
        }
        assertEquals(7, list1.size());

        list1.set(0, "Mike");
        assertNotEquals("Clive", list1.get(0));

        list1.remove("Mike");
        assertEquals(-1, list1.indexOf("Mike"));

        String[] names = new String[list1.size()];
        list1.toArray(names);
        assertEquals(list1.size(), names.length);
        assertEquals(list1.get(0), names[0]);

        ArrayList<String> list2 = new ArrayList<>(list1);
        assertEquals(list1.size(), list2.size());

        list2.add("Clive");
        list2.removeAll(list1);
        assertEquals(1, list2.size());

        ArrayList<String> list3 = new ArrayList<>(6);
        list3.addAll(list2);
        list3.clear();
        assertEquals(0, list3.size());

    }

    @Test
    public void hashMapTest (){
        HashMap<String, String> map1 = new HashMap<>();
        map1.put("Java", "Джава");
        map1.put("Collections", "Коллекции");
        map1.put("HashMap", "ХэшКарта");
        assertEquals(3, map1.size());

        String value = map1.get("Java");
        assertEquals("Джава", value);

        boolean isEmpty = map1.isEmpty();
        assertFalse(isEmpty);

        Set<String> keys = map1.keySet();
        assertEquals(3, keys.size());

        Collection<String> values = map1.values();
        assertEquals(3, values.size());

        Set set = map1.entrySet();
        assertEquals(3, set.size());

        boolean containsKey = map1.containsKey("Java");
        boolean containsValue = map1.containsValue("Джава");
        assertTrue(containsKey);
        assertTrue(containsValue);

        map1.remove("Java");
        assertEquals(2, map1.size());

        HashMap<String, String> map2 = (HashMap<String, String>) map1.clone();
        boolean containsKeyClone = map1.containsKey("HashMap");
        boolean containsValueClone = map1.containsValue("Коллекции");
        assertTrue(containsKeyClone);
        assertTrue(containsValueClone);
        assertEquals(2, map2.size());

        HashMap<String, String> map3 = new HashMap<>(map2);
        assertEquals(2, map3.size());

        HashMap<String, String> map4 = new HashMap<>(3);
        map4.putAll(map3);
        map4.put("Java", "Джава");
        assertEquals(3, map4.size());

        HashMap<String, String> map5 = new HashMap<>(3, 0.1f);

    }

    @Test
    public void treeSetTest (){

        TreeSet<Integer> set1 = new TreeSet<>();
        set1.add(6);
        set1.add(3);
        set1.add(9);
        set1.add(1);
        assertEquals(4, set1.size());

        boolean contains = set1.contains(1);
        assertTrue(contains);


        Integer first = set1.first();
        assertEquals(1, first.intValue());

        Integer last = set1.last();
        assertEquals(9, last.intValue());

        boolean isEmpty = set1.isEmpty();
        assertFalse(isEmpty);
        SortedSet<Integer> headSet = set1.headSet(6);
        assertEquals(2, headSet.size());

        SortedSet<Integer> subSet = set1.subSet(3, 9);
        assertEquals(2, subSet.size());

        SortedSet<Integer> tailSet = set1.tailSet(3);
        assertEquals(3, tailSet.size());

        TreeSet<Integer> set2 = new TreeSet<>(set1);
        assertEquals(4, set2.size());
        set2.clear();
        assertEquals(0, set2.size());

        TreeSet<Integer> set3 = new TreeSet<>((o1, o2) -> {
            if (o1 < o2){
                return 1;
            } else if (o1 > o2) {
                return -1;

            } else {
            return 0;
            }
        });

        set3.addAll(set1);

        Integer firstAfterCompare = set3.first();
        assertEquals(9, firstAfterCompare.intValue());

        Integer lastAfterCompare = set3.last();
        assertEquals(1, lastAfterCompare.intValue());

    }

    @Test
    public void collectionsTest (){

        ArrayList<String> list = new ArrayList<>();
        list.add("Tom");
        list.add("Bob");
        list.add("Jack");

        sort(list);
        assertEquals("Bob", list.get(0));

        String min = min(list);
        assertEquals("Bob", min);

        String max = max(list);
        assertEquals("Tom", max);

        reverse(list);
        assertEquals("Bob", list.get(2));

        ArrayList<String> list2 = new ArrayList<>(list);
        shuffle(list);
        assertNotEquals(list2, list);

        swap(list2, list2.indexOf("Jack"), list2.indexOf("Tom"));
        assertNotEquals("Tom", list2.get(0));

        ArrayList<String> test = new ArrayList<>();
        test.add("Fill");
        test.add("Bob");
        test.add("Vincent");

        Boolean disjoint = disjoint(test, list);
        assertFalse(disjoint);

        int bob = binarySearch(test, "Bob");
        assertEquals(1, bob);

        fill(test, "Sam");
        assertEquals("Sam", test.get(0));
        assertEquals("Sam", test.get(1));
        assertEquals("Sam", test.get(2));

        int frequency = frequency(test, "Sam");
        assertEquals(3, frequency);

        test.addAll(2, list);
        assertEquals("Tom", test.get(2));

        int indexOfSubList = indexOfSubList(test, list);
        assertEquals(2, indexOfSubList);

        test.addAll(5, list);
        int lastIndexOfSubList = lastIndexOfSubList(test, list);
        assertEquals(5, lastIndexOfSubList);
    }

}
