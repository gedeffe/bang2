package com.supinfo.java.day1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RationalTests {

    @Test
    void testOpposite_thenOk() {
        // Given
        Rational rational = new Rational(45.6);

        // When
        double opposite = rational.opposite();

        // Then
        Assertions.assertEquals(-45.6, opposite);
    }

    @Test
    void testReduce_thenIrreducible() {
        // Given
        Rational rational = new Rational(17);

        // When
        String reduce = rational.reduce();

        // Then
        Assertions.assertNotNull(reduce);
        Assertions.assertEquals("17/1", reduce);
    }

    @Test
    void testReduce_thenOutOfBounds() {
        // Given
        Rational rational = new Rational(2d / 3);

        // When + Then
        Assertions.assertThrows(StackOverflowError.class, rational::reduce);
    }

    @Test
    void testReduce_thenReduced() {
        // Given
        Rational rational = new Rational(45.6);

        // When
        String reduce = rational.reduce();

        // Then
        Assertions.assertNotNull(reduce);
        Assertions.assertEquals("228/5", reduce);
    }

    @Test
    void testMultiply_thenOk() {
        // Given
        Rational rational = new Rational(4);

        // When
        double multiply = rational.multiply(0.5);

        // Then
        Assertions.assertEquals(2, multiply);
    }

    @Test
    void testDivide_thenOk() {
        // Given
        Rational rational = new Rational(4);

        // When
        double divide = rational.divide(0.5);

        // Then
        Assertions.assertEquals(8, divide);
    }

    @Test
    void testDivide_thenManageZero() {
        // Given
        Rational rational = new Rational(4);

        // When
        double divide = rational.divide(0);

        // Then
        Assertions.assertEquals(Double.NaN, divide);
    }
}
