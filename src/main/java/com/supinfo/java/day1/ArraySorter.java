package com.supinfo.java.day1;

import java.util.Random;

/**
 * Exercise 6 : Sorting
 * Create an array of 20 integers. Add the following methods:
 * <p>
 * init() that instantiates the array and fills it random values.
 * bubbleSort() that sorts the array by the bubble sort method.
 * selectionSort() that sorts the array using the selection sort method.
 */
public class ArraySorter {
    private int[] array;

    public int[] init() {
        int length = 20;
        this.array = new Random().ints(length).toArray();
        return this.array;
    }

    public int[] bubbleSort() {
        if (this.array == null) {
            throw new IllegalArgumentException("Array should be initialized first, please call init method.");
        }

        int n = this.array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (this.array[j] > this.array[j + 1]) {
                    // swap values
                    int temp = this.array[j];
                    this.array[j] = this.array[j + 1];
                    this.array[j + 1] = temp;
                }
            }
        }
        return this.array;
    }

    public int[] selectionSort() {
        if (this.array == null) {
            throw new IllegalArgumentException("Array should be initialized first, please call init method.");
        }

        int n = this.array.length;

        // One by one move boundary of unsorted subarray
        for (int i = 0; i < n - 1; i++) {
            // Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i + 1; j < n; j++)
                if (this.array[j] < this.array[min_idx])
                    min_idx = j;

            // Swap the found minimum element with the first
            // element
            int temp = this.array[min_idx];
            this.array[min_idx] = this.array[i];
            this.array[i] = temp;
        }
        return this.array;
    }
}
