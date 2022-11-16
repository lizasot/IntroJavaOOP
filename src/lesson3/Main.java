package lesson3;

import lesson1and2.People;

import java.util.*;

public class Main {
    public static void SortByLastNumber(int[] array) {
        Integer[] arrayForSort = new Integer[array.length];
        for (int i = 0; i < array.length; i++) {
            arrayForSort[i] = array[i];
        }
        Arrays.sort(arrayForSort, new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                Integer oo1 = o1 % 10;
                Integer oo2 = o2 % 10;
                return (oo1).compareTo(oo2);
            }
        });
        for (int i = 0; i < array.length; i++) {
            array[i] = arrayForSort[i];
        }
    }
    public static void task1() {
        int[] array = new int[]{61,83,45,90,81};
        SortByLastNumber(array);
        for (int item:array) {
            System.out.println(item);
        }
    }
    public static void main(String[] args) {
        MyCollection<People> test = new MyCollection<People>();
        test.add(new People("Anya", true));
        test.add(new People("Marya", true));
        test.get(0).addParent(test.get(1));
        for (People item:test) {
            System.out.println(item.getFullInfo());
        }
    }
}