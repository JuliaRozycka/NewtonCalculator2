package edu.ib.newtoncalc;

import java.io.Serializable;

public class Polynomial implements Serializable {
    private String[] coefficients;

    public Polynomial(String[] coefficients) {
        this.coefficients = coefficients;
    }

    public double valueX(double x){
        double sum = 0;
        for (int i = 0; i<coefficients.length; i++)
            sum += Double.parseDouble(coefficients[i])*Math.pow(x,coefficients.length - 1 - i);
       return sum;
    }

    @Override
    public String toString() {
        String plus = "+";
        for (int i = 0; i < coefficients.length; i++) {
            if (Double.parseDouble(coefficients[i]) >= 0) {
                coefficients[i] = plus.concat(coefficients[i]);
            }
        }
        String result = "";
        for (int i = 0; i < coefficients.length; i++) {
            result += coefficients[i] + "x^" + (coefficients.length - 1 - i);
        }
        return result.replace("+","%2B");
    }
}
