package edu.ib.newtoncalc;

import java.io.Serializable;

public class Polynomial implements Serializable {
    private final String[] coefficients;

    public Polynomial(String[] coefficients) {
        this.coefficients = coefficients;
    }

    public double calculateForValueX(double x) {
        double sum = 0;
        for (int i = 0; i < coefficients.length; i++)
            sum += Double.parseDouble(coefficients[i]) * Math.pow(x, coefficients.length - 1 - i);
        return sum;
    }

    public String substituteForValueX(String x) {
       return toString().replace("x",("("+x+")"));
    }

    @Override
    public String toString() {
        String plus = "+";
        for (int i = 0; i < coefficients.length; i++) {
            if (Double.parseDouble(coefficients[i]) >= 0) {
                coefficients[i] = plus.concat(coefficients[i]);
            }
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < coefficients.length; i++) {
            result.append(coefficients[i]).append("x^").append(coefficients.length - 1 - i);
        }
        return result.toString().replace("+", "%2B");
    }
}
