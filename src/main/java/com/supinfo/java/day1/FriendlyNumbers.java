package com.supinfo.java.day1;

import java.util.HashMap;
import java.util.Map;

/**
 * Exercise 5 : Friendly Numbers
 * <p>
 * two integers a , b are said to be friendly if:
 * <p>
 * a, b are strictly positive  and a<>b.
 * the sum m of the strict divisors of a  is equal to the sum of the divisors of b.
 * the sum of the two numbers is m.
 * <p>
 * Write a  java program which determines all pairs <a, b> of friendly numbers < 10 000
 * How many pairs are there?
 */
public class FriendlyNumbers {
    // to store sum of divisors and avoid multiple computation
    private final Map<Integer, Integer> divisors = new HashMap<>();

    public static void main(final String[] args) {
        final int[] numbers = new int[10000];
        for (int i = 1; i < numbers.length; i++) {
            numbers[i - 1] = i;
        }
        System.out.println("Les couples des numbers amicaux sont : ");
        final FriendlyNumbers friendlyNumbers
                = new FriendlyNumbers();
        friendlyNumbers.displayFriends(numbers);


    }

    public boolean checkFriendliness(int nb1, int nb2) {
        final int somme = this.sumDivisors(nb1);
        return (nb1 + nb2 == somme
                && this.sumDivisors(nb2) == somme);
    }

    public int sumDivisors(final int nb1) {
        int sum;
        if (this.divisors.containsKey(nb1)) {
            sum = this.divisors.get(nb1);
        } else {
            sum = 0;
            for (int i = 1; i <= nb1; ++i) {
                if ((nb1 % i) == 0) {
                    sum += i;
                }
            }
            this.divisors.put(nb1, sum);
        }
        return sum;
    }


    public void displayFriends(final int[] numbers) {
        for (int i = 0; i < numbers.length; ++i) {
            for (int j = i + 1; j < numbers.length; ++j) {
                if (this.checkFriendliness(numbers[i], numbers[j])) {
                    System.out.println(numbers[i] + " " + numbers[j]);
                }
            }
        }
    }
}
