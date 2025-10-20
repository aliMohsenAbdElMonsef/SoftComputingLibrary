package org.example.case_studies.GA.functions;

public class Function1 implements  Functions{

    @Override
    public double method(double x1, double x2, double x3, double x4) {
        return Math.sin(Math.toRadians(x1)) + Math.cos(Math.toRadians(x2)) + Math.pow(x3,2) - Math.sqrt(x4);
    }
    @Override
    public double validateInput(double x1, double x2, double x3, double x4) {
        // Disallow NaN or Infinite inputs
        if (Double.isNaN(x1) || Double.isNaN(x2) || Double.isNaN(x3) || Double.isNaN(x4) ||
            Double.isInfinite(x1) || Double.isInfinite(x2) || Double.isInfinite(x3) || Double.isInfinite(x4)) {
            throw new IllegalArgumentException("Invalid input: values must be finite real numbers.");
        }
        // Domain: sqrt(x4)
        if (x4 < 0) {
            throw new IllegalArgumentException("Invalid input: x4 must be >= 0 for sqrt(x4).");
        }
        // Prevent overflow in pow(x3, 2)
        double SQRT_MAX = Math.sqrt(Double.MAX_VALUE);
        if (Math.abs(x3) > SQRT_MAX) {
            throw new IllegalArgumentException("Invalid input: |x3| too large; may overflow x3^2.");
        }
        return method(x1, x2, x3, x4);
    }
}
