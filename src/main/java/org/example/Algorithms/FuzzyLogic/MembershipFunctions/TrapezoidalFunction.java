package org.example.FuzzyLogic.MembershipFunctions;

import java.util.ArrayList;
import java.util.List;

public class TrapezoidalFunction implements Function {
    private double a, b, c, d;
    private String name;

    public TrapezoidalFunction(String _name, double _a, double _b, double _c, double _d) {
        name = _name;
        a = _a;
        b = _b;
        c = _c;
        d = _d;
        if(!(a<=b && b<=c && c<=d))
        {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public double getCentroid() {
        return (a + b + c + d) / 4;
    }

    @Override
    public List<Double> getMembership() {
        List<Double> membership = new ArrayList<>();
        membership.add(a);
        membership.add(b);
        membership.add(c);
        membership.add(d);
        return membership;
    }

    @Override
    public double getMaxPoint() {
        return (c + b) / 2;
    }

    @Override
    public double fuzzify(double x) {
        if (x <= a) {
            return 0.0;
        } else if (x < b) {
            return (x - a) / (b - a);
        } else if (x <= c) {
            return 1.0;
        } else if (x < d) {
            return (d - x) / (d - c);
        } else {
            return 0.0;
        }
    }

    @Override
    public String getName() {
        return name;
    }
}
