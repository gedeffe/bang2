package com.supinfo.java.day1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class ArraySorterTests {
    @Test
    void testInit_thenArray() {
        // Given
        ArraySorter arraySorter = new ArraySorter();

        // When
        int[] array = arraySorter.init();

        // Then
        Assertions.assertNotNull(array);
        Assertions.assertEquals(20, array.length);
        // not already sorted ...
        int[] sortedArray = Arrays.stream(array).sorted().toArray();
        boolean equals = true;
        for (int index = 0; index < array.length; index++) {
            equals = equals && array[index] == sortedArray[index];
        }
        Assertions.assertFalse(equals);
    }

    @Test
    void testBubbleSort_thenOk() {
        // Given
        ArraySorter arraySorter = new ArraySorter();

        arraySorter.init();

        // When
        int[] array = arraySorter.bubbleSort();

        // Then
        Assertions.assertNotNull(array);
        Assertions.assertEquals(20, array.length);
        // sorted ...
        int[] sortedArray = Arrays.stream(array).sorted().toArray();
        Assertions.assertArrayEquals(sortedArray, array);
    }

    @Test
    void testSelectionSort_thenOk() {
        // Given
        ArraySorter arraySorter = new ArraySorter();

        arraySorter.init();

        // When
        int[] array = arraySorter.selectionSort();

        // Then
        Assertions.assertNotNull(array);
        Assertions.assertEquals(20, array.length);
        // sorted ...
        int[] sortedArray = Arrays.stream(array).sorted().toArray();
        Assertions.assertArrayEquals(sortedArray, array);
    }
}
