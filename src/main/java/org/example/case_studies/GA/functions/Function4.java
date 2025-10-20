package org.example.case_studies.GA.functions;

public class Function4 implements Functions{

    @Override
    public double method(double x1, double x2, double x3, double x4) {
            return Math.sin(Math.toRadians(x1))*Math.cos(Math.toRadians(x2))+Math.sqrt(x3)*Math.pow(x4, 2);
    }
    @Override
    public double validateInput(double x1, double x2, double x3, double x4) {
            // Disallow NaN or Infinite inputs
            if (Double.isNaN(x1) || Double.isNaN(x2) || Double.isNaN(x3) || Double.isNaN(x4) ||
                Double.isInfinite(x1) || Double.isInfinite(x2) || Double.isInfinite(x3) || Double.isInfinite(x4)) {
                throw new IllegalArgumentException("Invalid input: values must be finite real numbers.");
            }
            // Domain for sqrt(x3)
            if (x3 < 0) {
                throw new IllegalArgumentException("Invalid input: x3 must be >= 0 for sqrt(x3).");
            }
            // Prevent overflow in pow(x4, 2)
            double SQRT_MAX = Math.sqrt(Double.MAX_VALUE);
            if (Math.abs(x4) > SQRT_MAX) {
                throw new IllegalArgumentException("Invalid input: |x4| too large; may overflow x4^2.");
            }
            // Prevent overflow in sqrt(x3) * (x4^2)
            double x4sq = x4 * x4; // safe due to previous check
            double limit = Double.MAX_VALUE / x4sq; // maximum allowed sqrt(x3)
            if (Math.sqrt(x3) > limit) {
                throw new IllegalArgumentException("Invalid input: sqrt(x3)*x4^2 too large; may overflow.");
            }
            return method(x1, x2, x3, x4);
    }
}
