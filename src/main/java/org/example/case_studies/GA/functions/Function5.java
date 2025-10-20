package org.example.case_studies.GA.functions;

public class Function5 implements Functions{
    @Override
    public double method(double x1, double x2, double x3, double x4) {
            return Math.sin(Math.toRadians(Math.sin(Math.toRadians(x1))*Math.cos(Math.toRadians(Math.sqrt(x2)))))+Math.sin(Math.toRadians(x3*x4));
    }
    @Override
    public double validateInput(double x1, double x2, double x3, double x4) {
            // Disallow NaN or Infinite inputs
            if (Double.isNaN(x1) || Double.isNaN(x2) || Double.isNaN(x3) || Double.isNaN(x4) ||
                Double.isInfinite(x1) || Double.isInfinite(x2) || Double.isInfinite(x3) || Double.isInfinite(x4)) {
                throw new IllegalArgumentException("Invalid input: values must be finite real numbers.");
            }
            // Domain for sqrt(x2)
            if (x2 < 0) {
                throw new IllegalArgumentException("Invalid input: x2 must be >= 0 for sqrt(x2).");
            }
            // Prevent overflow in x3 * x4 used before toRadians
            double ax3 = Math.abs(x3);
            double ax4 = Math.abs(x4);
            if (ax4 > 0 && ax3 > Double.MAX_VALUE / ax4) {
                throw new IllegalArgumentException("Invalid input: x3*x4 magnitude too large; may overflow.");
            }
            return method(x1, x2, x3, x4);
    }
}
