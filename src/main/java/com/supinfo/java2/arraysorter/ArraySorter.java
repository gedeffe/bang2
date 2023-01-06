package com.supinfo.java2.arraysorter;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class ArraySorter {
    public static void main(String[] args) {
        ArraySorter arraySorter = new ArraySorter();
        arraySorter.launchArraySortComparison(100000);
    }

    public void launchArraySortComparison(int arrayLength) {
        int[] array = new int[arrayLength];
        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
        for (int i = 0; i < arrayLength; i++) {
            array[i] = threadLocalRandom.nextInt(101);
        }
        System.out.println("original array:");
        displayArray(array);
        System.out.println("sorted array:");
        long begin = System.nanoTime();
        Instant start = Instant.now();
        displayArray(sortWithBubbleProcess(
                Arrays.copyOf(array, arrayLength)));
        //long time = System.nanoTime() - begin;
        Instant finish = Instant.now();
        long time = Duration.between(start, finish).toMillis();
        System.out.println("Bubble sort = " + time);

        begin = System.nanoTime();
        start = Instant.now();
        displayArray(sortWithSelectionProcess(
                Arrays.copyOf(array, arrayLength)));
        finish = Instant.now();
        time = Duration.between(start, finish).toMillis();
        System.out.println("Selection sort = " + time);

        begin = System.nanoTime();
        start = Instant.now();
        displayArray(sortWithSInsertionProcess(
                Arrays.copyOf(array, arrayLength)));
        finish = Instant.now();
        time = Duration.between(start, finish).toMillis();
        System.out.println("Insertion sort = " + time);

        begin = System.nanoTime();
        start = Instant.now();
        displayArray(quickSort(
                Arrays.copyOf(array, arrayLength), 0, arrayLength - 1));
        finish = Instant.now();
        time = Duration.between(start, finish).toMillis();
        System.out.println("Quick sort = " + time);
    }

    private static void displayArray(int[] array) {
        //System.out.println(Arrays.toString(array));
    }

    private static int[] sortWithBubbleProcess(int[] array) {
        for (int i = 0; i < array.length - 1; ++i) {

            for (int j = 0; j < array.length - i - 1; ++j) {

                if (array[j + 1] < array[j]) {

                    int swap = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = swap;

                }
            }
        }

        return array;
    }

    private static int[] sortWithSelectionProcess(int[] array) {
        int n = array.length;

        // One by one move boundary of unsorted subarray
        for (int i = 0; i < n - 1; i++) {
            // Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i + 1; j < n; j++)
                if (array[j] < array[min_idx])
                    min_idx = j;

            // Swap the found minimum element with the first
            // element
            int temp = array[min_idx];
            array[min_idx] = array[i];
            array[i] = temp;
        }

        return array;
    }

    private static int[] sortWithSInsertionProcess(int[] array) {
        int n = array.length;
        for (int i = 1; i < n; ++i) {
            int key = array[i];
            int j = i - 1;

			/* Move elements of array[0..i-1], that are
			greater than key, to one position ahead
			of their current position */
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = key;
        }

        return array;
    }


    // A utility function to swap two elements
    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /* This function takes last element as pivot, places
    the pivot element at its correct position in sorted
    array, and places all smaller (smaller than pivot)
    to left of pivot and all greater elements to right
    of pivot */
    static int partition(int[] arr, int low, int high) {

        // pivot
        int pivot = arr[high];

        // Index of smaller element and
        // indicates the right position
        // of pivot found so far
        int i = (low - 1);

        for (int j = low; j <= high - 1; j++) {

            // If current element is smaller
            // than the pivot
            if (arr[j] < pivot) {

                // Increment index of
                // smaller element
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return (i + 1);
    }

    /* The main function that implements QuickSort
            arr[] --> Array to be sorted,
            low --> Starting index,
            high --> Ending index
    */
    static int[] quickSort(int[] array, int low, int high) {
        if (low < high) {

            // pi is partitioning index, arr[p]
            // is now at right place
            int pi = partition(array, low, high);

            // Separately sort elements before
            // partition and after partition
            quickSort(array, low, pi - 1);
            quickSort(array, pi + 1, high);
        }
        return array;
    }


}

