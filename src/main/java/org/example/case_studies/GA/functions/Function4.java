package org.example.case_studies.GA.functions;

public class Function4 implements Functions{

    @Override
    public double method(double x1, double x2, double x3, double x4) {
            return Math.sin(x1)*Math.cos(x2)+Math.sqrt(x3)*Math.pow(x4, 2);
    }
}
