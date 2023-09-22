package com.hashem;

public class Program {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}

class Test {
    static void swap(int a, int b) {
        int t = a;
        a = b;
        b = t;
    }
    public static void main(String[] args) {
        int x = 1;
        int y = 2;

        System.out.println("pre:  x = " + x + " y = " + y);
        swap(x, y);
        System.out.println("post: x = " + x + " y = " + y);
    }
}