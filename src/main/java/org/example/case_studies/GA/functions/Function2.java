package org.example.case_studies.GA.functions;

public class Function2 implements Functions{
    @Override
    public double method(double x1, double x2, double x3, double x4) {
            return Math.sin(Math.toRadians(x1 * x2)) + Math.cos(Math.toRadians(x3)) + .5 * (double) Math.pow(x4, 2);
    }
    @Override
    public double validateInput(double x1, double x2, double x3, double x4) {
            // Disallow NaN or Infinite inputs
            if (Double.isNaN(x1) || Double.isNaN(x2) || Double.isNaN(x3) || Double.isNaN(x4) ||
                Double.isInfinite(x1) || Double.isInfinite(x2) || Double.isInfinite(x3) || Double.isInfinite(x4)) {
                throw new IllegalArgumentException("Invalid input: values must be finite real numbers.");
            }
            // Prevent overflow in x1 * x2 used before toRadians
            double ax1 = Math.abs(x1);
            double ax2 = Math.abs(x2);
            if (ax2 > 0 && ax1 > Double.MAX_VALUE / ax2) {
                throw new IllegalArgumentException("Invalid input: x1*x2 magnitude too large; may overflow.");
            }
            // Prevent overflow in pow(x4, 2)
            double SQRT_MAX = Math.sqrt(Double.MAX_VALUE);
            if (Math.abs(x4) > SQRT_MAX) {
                throw new IllegalArgumentException("Invalid input: |x4| too large; may overflow x4^2.");
            }
            return method(x1, x2, x3, x4);
    }
}
