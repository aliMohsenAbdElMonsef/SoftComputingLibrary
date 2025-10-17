package org.example.case_studies.GA.functions;

public class Function5 implements Functions{
    @Override
    public double method(double x1, double x2, double x3, double x4) {
            return Math.sin(Math.sin(x1)*Math.cos(Math.sqrt(x2)))+Math.sin(x3*x4);
    }
}
