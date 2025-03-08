package com.hashem.firstandroidapp;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;


public class JavaTest {
    @Test
    public void test() {
        System.out.println("Hello from JavaTest");

        Assert.assertTrue(true);

        String stringOne = "Hello";
        String stringTwo = "Hello";

        System.out.println(stringOne == stringTwo);
        System.out.println(stringOne.equals(stringTwo));

        ArrayDeque<String> deque = new ArrayDeque<>();
        deque.add("Hello");
        deque.add("World");
        deque.add("!");
        while (!deque.isEmpty()) {
            System.out.println(deque.pollFirst());
        }
        System.out.println();

        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("Hello");
        linkedList.add("World");
        linkedList.add("!");
        while (!linkedList.isEmpty()) {
            System.out.println(linkedList.pollFirst());
        }
        System.out.println();

        Set<String> set = new HashSet<>();
        set.add("Hello");
        set.add("World");
        set.add("!");
        for (String s : set) {
            System.out.println(s);
        }
        System.out.println();

        var sortedSet = new TreeSet<String>();
        sortedSet.add("Hello");
        sortedSet.add("World");
        sortedSet.add("!");
        for (String s : sortedSet) {
            System.out.println(s);
        }
        System.out.println();

        for (int i = 0; i < 10; i++) {
            System.out.println(i);
        }

    }
}
