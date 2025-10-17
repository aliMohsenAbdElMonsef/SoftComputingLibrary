package org.example.case_studies.GA.functions;

public class Function2 implements Functions{
    @Override
    public double method(double x1, double x2, double x3, double x4) {
            return Math.sin(x1 * x2) + Math.cos(x3) + .5 * (double) Math.pow(x4, 2);
    }
}
