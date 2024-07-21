package com.hashem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
    Booking interview solutions
 */
public class BookingInterview {
    static void asd() {
        int[] numbers = { 25626, 25757, 24367, 24267, 16, 100, 2, 7277 };
        int x;

        String strings = String.valueOf(numbers[0]);

        for (int i = 1; i < numbers.length; i++) {
            x = numbers[i] - numbers[i - 1];
            if (x >= 127 && x <= -127)
                strings += " -128 " + x;
            else
                strings += " " + x;
        }

        System.out.printf(strings);
    }

    public static View findViewById(ViewGroup root, int id) {
        // implement this method only

        View view = null;
        for (int i = 0; i < root.getChildCount(); i++) {
            if (root.getChildAt(i) instanceof ViewGroup) {
                view = findViewById((ViewGroup) root.getChildAt(i), id);
            } else if (root.getChildAt(i).getId() == id) {
                view = root.getChildAt(i);
                break;
            }
        }

        return view;
    }

    private class View {
        private ViewGroup parent;
        private String name;
        private int id;

        View(ViewGroup parent) {
            this.parent = parent;
        }

        public int getId() {
            return id;
        }

        public ViewGroup getParent() {
            return parent;
        }

        void setId(int id) {
            this.id = id;
        }

        void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    private class ViewGroup extends View {
        private ArrayList<View> children = new ArrayList<>();

        ViewGroup(ViewGroup parent) {
            super(parent);
        }

        void addChild(View child) {
            children.add(child);
        }

        public int getChildCount() {
            return children.size();
        }

        public View getChildAt(int index) {
            return children.get(index);
        }
    }
}
