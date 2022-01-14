package com.supinfo.java.day1;

/**
 * Instantiate two rationals a, b.
 * Add the following methods to the Rational class:
 * 1. opposite () which returns the opposite of the current rational.
 * 2. reduce () which puts the rational as an irreducible fraction.
 * 3. multiply (Rational r) which returns the product of the current rational with the rational r.
 * 4. divide (Rational r) which  returns the quotient of the current rational with the rational r.
 */
public class Rational {
    private final double internalValue;

    public Rational(double provided) {
        this.internalValue = provided;
    }

    public double opposite() {
        return 0 - this.internalValue;
    }

    public String reduce() {
        final RationalReductor rationalReductor = new RationalReductor();
        return rationalReductor.reduce(this.internalValue);
    }

    public double multiply(double value) {
        return this.internalValue * value;
    }

    public double divide(double value) {
        double divide = Double.NaN;
        if (value != 0) {
            divide = this.internalValue / value;
        }
        return divide;
    }
}
