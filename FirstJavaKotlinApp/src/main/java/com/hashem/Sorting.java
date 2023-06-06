package com.hashem;

public class Sorting {

    public static void main(String[] args) {
        int[] unsortedList = new int[]{5, 2, 6, 3, 4, 1};
        int n = unsortedList.length;

        for (int i : unsortedList) {
            System.out.print(i + " ");
        }
        System.out.println("\n----------------Before");

        selectionSort(unsortedList, n);

        System.out.println("\n----------------After");
        for (int i : unsortedList)
            System.out.print(i + " ");
    }

    private static void selectionSort(int[] a, int n) {
        for (int i = 0; i < n; i++) {
            int min_index = i;
            for (int j = i; j < n; j++) {
                if (a[j] < a[min_index])
                    min_index = j;
            }

            int temp = a[i];
            a[i] = a[min_index];
            a[min_index] = temp;

            // rounds
            for (int k = 0; k < n; k++)
                System.out.print(a[k] + " ");
            System.out.println();
        }
    }

    private static void insertionSort(int[] a, int n) {
        for (int i = 1; i < n; i++) {
            int j = i;
            while (j > 0) {
                if (a[j - 1] > a[j]) {
                    int temp = a[j - 1];
                    a[j - 1] = a[j];
                    a[j] = temp;
                    j--;
                } else
                    break;
            }

            for (int k = 0; k < n; k++)
                System.out.print(a[k] + " ");
            System.out.println();
        }
    }

    private static void bubbleSort(int[] a, int n) {
        for (int i = 0; i < n - 1; i++) {
            for (int x = 0; x < n - i - 1; x++) {
                if (a[x + 1] < a[x]) {
                    int temp = a[x + 1];
                    a[x + 1] = a[x];
                    a[x] = temp;
                }

                for (int k = 0; k < n; k++)
                    System.out.print(a[k] + " ");
                System.out.println();
            }

            // rounds
            System.out.println();
            for (int k = 0; k < n; k++)
                System.out.print(a[k] + " ");
            System.out.println();
        }
    }
}
