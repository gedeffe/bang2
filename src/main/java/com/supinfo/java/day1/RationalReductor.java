package com.supinfo.java.day1;

public class RationalReductor {
    int quotient;
    int denominator;

    public String reduce(double value) {

        this.transformRationalIntoQuotientForm(value);
        this.applyGreaterCommonDenominatorReduction();
        return this.quotient + "/" + this.denominator;
    }

    void applyGreaterCommonDenominatorReduction() {
        int greaterCommonDenominator = this.computeGreaterCommonDenominator(this.quotient, this.denominator);
        this.quotient = this.quotient / greaterCommonDenominator;
        this.denominator = this.denominator / greaterCommonDenominator;
    }

    void transformRationalIntoQuotientForm(double value) {
        this.transformRationalIntoQuotientForm(value, 1); // initial value of coefficient should be 1 (always !).
    }

    /**
     * @param value       which will be used to deduce quotient value.
     * @param coefficient which will be used to deduce denominator value.
     */
    void transformRationalIntoQuotientForm(double value, int coefficient) {
        /*
        If current value is already an integer, we cannot reduce it.
         If it is a real double, we should be able to transform it into an integer to get a rational form.
         */
        if (this.isAnInteger(value)) {
            this.quotient = (int) value;
            this.denominator = coefficient;
        } else {
            double shiftedDecimal = value * 10;
            this.transformRationalIntoQuotientForm(shiftedDecimal, 10 * coefficient);
        }
    }

    /**
     * @param m is any integer strictly bigger than n.
     * @param n is any integer strictly smaller than n.
     * @return is a positive int between 1 and m.
     */
    int computeGreaterCommonDenominator(final int m, final int n) {
        int maxValue = Math.abs(m);
        int minValue = Math.abs(n);
        int result = maxValue;
        if (minValue != 0) {
            int leftOver = maxValue % minValue;
            result = computeGreaterCommonDenominator(minValue, leftOver);
        }
        return result;
    }

    boolean isAnInteger(double value) {
        int onlyIntegerPart = (int) value;
        double rest = value - onlyIntegerPart;
        return rest == 0;
    }


}