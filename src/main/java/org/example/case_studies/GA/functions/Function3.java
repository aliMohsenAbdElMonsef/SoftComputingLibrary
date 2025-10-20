package org.example.case_studies.GA.functions;

public class Function3 implements Functions{
    @Override
    public double method(double x1, double x2, double x3, double x4) {
            return x1 + Math.pow(x2, 2) + Math.pow(x3, 3) + Math.pow(x4, 4);
    }
    @Override
    public double validateInput(double x1, double x2, double x3, double x4) {
            // Disallow NaN or Infinite inputs
            if (Double.isNaN(x1) || Double.isNaN(x2) || Double.isNaN(x3) || Double.isNaN(x4) ||
                Double.isInfinite(x1) || Double.isInfinite(x2) || Double.isInfinite(x3) || Double.isInfinite(x4)) {
                throw new IllegalArgumentException("Invalid input: values must be finite real numbers.");
            }
            // Prevent overflow in powers
            double SQRT_MAX = Math.sqrt(Double.MAX_VALUE);
            double CBRT_MAX = Math.cbrt(Double.MAX_VALUE);
            double FOURTH_MAX = Math.pow(Double.MAX_VALUE, 0.25);
            if (Math.abs(x2) > SQRT_MAX) {
                throw new IllegalArgumentException("Invalid input: |x2| too large; may overflow x2^2.");
            }
            if (Math.abs(x3) > CBRT_MAX) {
                throw new IllegalArgumentException("Invalid input: |x3| too large; may overflow x3^3.");
            }
            if (Math.abs(x4) > FOURTH_MAX) {
                throw new IllegalArgumentException("Invalid input: |x4| too large; may overflow x4^4.");
            }
            return method(x1, x2, x3, x4);
    }
}
