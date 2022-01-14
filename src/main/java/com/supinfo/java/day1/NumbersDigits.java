package com.supinfo.java.day1;

/**
 * Exercise 2 : numbers and digits
 * <p>
 * 1. Write a  function int units (int n)  returning the unit digit of  n.
 * 2. Write a  function  int tens (int n) returning the digit of tens of  n.
 * 3. Write a  function int nbDigit (int n) returning the number of digits of n.
 */
public class NumbersDigits {


    public int units(final int n) {
        return n % 10;
    }

    public int tens(final int n) {
        return (n / 10) % 10;
    }

    public int nbDigits(final int n) {
        return Integer.toString(n).length();
    }
}
