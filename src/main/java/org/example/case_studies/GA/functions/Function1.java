package org.example.case_studies.GA.functions;

public class Function1 implements  Functions{

    @Override
    public double method(double x1, double x2, double x3, double x4) {
            return Math.sin(Math.toRadians(x1)) + Math.cos(Math.toRadians(x2)) + Math.pow(x3,2) - Math.sqrt(x4);
    }
}
