package com.itu;

public class Person {
    String name;
    int[] preferences;
    boolean isMatched;
    boolean isMale;

    public Person(String name, int[] preferences, boolean isMale) {
        this.name = name;
        this.preferences = preferences;
        this.isMale = isMale;
    }

    public Person(String name, int[] preferences) {
        this.name = name;
        this.preferences = preferences;
    }
}
