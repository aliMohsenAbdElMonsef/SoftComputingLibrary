package org.example.case_studies.GA.functions;

public class Function3 implements Functions{
    @Override
    public double method(double x1, double x2, double x3, double x4) {
            return x1 + Math.pow(x2, 2) + Math.pow(x3, 3) + Math.pow(x4, 4);
    }
}
