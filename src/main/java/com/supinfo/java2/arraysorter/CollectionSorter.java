package com.supinfo.java2.arraysorter;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class CollectionSorter {
    public static void main(String[] args) {
        CollectionSorter arraySorter = new CollectionSorter();
        arraySorter.launchArraySortComparison(10);
    }

    public void launchArraySortComparison(int arrayLength) {
        List<Integer> collection = new ArrayList<>();
        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
        for (int i = 0; i < arrayLength; i++) {
            collection.add(threadLocalRandom.nextInt(101));
        }
        System.out.println("original collection:");
        displayList(collection);
        System.out.println("sorted collection:");
        long begin = System.nanoTime();
        Instant start = Instant.now();
        displayList(sortWithBubbleProcess(
                new ArrayList<Integer>(collection)));
        //long time = System.nanoTime() - begin;
        Instant finish = Instant.now();
        long time = Duration.between(start, finish).toMillis();
        System.out.println("Bubble sort = " + time);

        begin = System.nanoTime();
        start = Instant.now();
        displayList(sortWithSelectionProcess(
                new ArrayList<Integer>(collection)));
        finish = Instant.now();
        time = Duration.between(start, finish).toMillis();
        System.out.println("Selection sort = " + time);

        begin = System.nanoTime();
        start = Instant.now();
        displayList(sortWithSInsertionProcess(
                new ArrayList<Integer>(collection)));
        finish = Instant.now();
        time = Duration.between(start, finish).toMillis();
        System.out.println("Insertion sort = " + time);

        begin = System.nanoTime();
        start = Instant.now();
        displayList(quickSort(
                new ArrayList<Integer>(collection), 0, arrayLength - 1));
        finish = Instant.now();
        time = Duration.between(start, finish).toMillis();
        System.out.println("Quick sort = " + time);
    }

    private static void displayList(List<Integer> list) {
        System.out.println(list);
    }

    private static List<Integer> sortWithBubbleProcess(List<Integer> list) {
        for (int i = 0; i < list.size() - 1; ++i) {

            for (int j = 0; j < list.size() - i - 1; ++j) {

                if (list.get(j + 1) < list.get(j)) {

                    int swap = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, swap);

                }
            }
        }

        return list;
    }

    private static List<Integer> sortWithSelectionProcess(List<Integer> list) {
        int n = list.size();

        // One by one move boundary of unsorted subarray
        for (int i = 0; i < n - 1; i++) {
            // Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i + 1; j < n; j++)
                if (list.get(j) < list.get(min_idx))
                    min_idx = j;

            // Swap the found minimum element with the first
            // element
            int temp = list.get(min_idx);
            list.set(min_idx, list.get(i));
            list.set(i, temp);
        }

        return list;
    }

    private static List<Integer> sortWithSInsertionProcess(List<Integer> list) {
        int n = list.size();
        for (int i = 1; i < n; ++i) {
            int key = list.get(i);
            int j = i - 1;

			/* Move elements of array[0..i-1], that are
			greater than key, to one position ahead
			of their current position */
            while (j >= 0 && list.get(j) > key) {
                list.set(j + 1, list.get(j));
                j = j - 1;
            }
            list.set(j + 1, key);
        }

        return list;
    }


    // A utility function to swap two elements
    static void swap(List<Integer> list, int i, int j) {
        int temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    /* This function takes last element as pivot, places
    the pivot element at its correct position in sorted
    array, and places all smaller (smaller than pivot)
    to left of pivot and all greater elements to right
    of pivot */
    static int partition(List<Integer> list, int low, int high) {

        // pivot
        int pivot = list.get(high);

        // Index of smaller element and
        // indicates the right position
        // of pivot found so far
        int i = (low - 1);

        for (int j = low; j <= high - 1; j++) {

            // If current element is smaller
            // than the pivot
            if (list.get(j) < pivot) {

                // Increment index of
                // smaller element
                i++;
                swap(list, i, j);
            }
        }
        swap(list, i + 1, high);
        return (i + 1);
    }

    /* The main function that implements QuickSort
            arr[] --> Array to be sorted,
            low --> Starting index,
            high --> Ending index
    */
    static List<Integer> quickSort(List<Integer> list, int low, int high) {
        if (low < high) {

            // pi is partitioning index, arr[p]
            // is now at right place
            int pi = partition(list, low, high);

            // Separately sort elements before
            // partition and after partition
            quickSort(list, low, pi - 1);
            quickSort(list, pi + 1, high);
        }
        return list;
    }


}

