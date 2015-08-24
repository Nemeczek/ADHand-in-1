package com.itu;

import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        Integer n = 4;

        Person[] persons = new Person[8];

        persons[0] = new Person("Sheldon", new int[]{2, 4, 6, 8},true);
        persons[1] = new Person("Amy", new int[]{1, 7, 5, 3});
        persons[2] = new Person("Rajesh", new int[]{6, 4, 2, 8},true);
        persons[3] = new Person("Penny", new int[]{7, 3, 1, 5});
        persons[4] = new Person("Howard", new int[]{6, 4, 8, 2},true);
        persons[5] = new Person("Bernadette", new int[]{5, 3, 7, 1});
        persons[6] = new Person("Leonard", new int[]{4, 8, 6, 2},true);
        persons[7] = new Person("Emily", new int[]{7, 5, 1, 3});

        Stack<Person> availableMens = new Stack<>();

        for (int i=0;i<persons.length;i++){
            if(persons[i].isMale)
                availableMens.add(persons[i]);
        }





    }
}
