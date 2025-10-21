package org.example.Algorithms.GA.BusinessLogic.Implementation.Functions;

import org.example.Algorithms.GA.BusinessLogic.Contracts.Functions.Functions;
import org.example.Algorithms.GA.BusinessLogic.Implementation.Chromosomes.Range;

public class Function4 implements Functions{

    @Override
    public double method(double x1, double x2, double x3, double x4) {
        return Math.sin(Math.toRadians(x1))*Math.cos(Math.toRadians(x2))+Math.sqrt(x3)*Math.pow(x4, 2);
    }
    @Override
    public Range[] validateInput(Range[] ranges) {
        if (ranges == null || ranges.length != 4) {
            throw new IllegalArgumentException("ranges must be length 4");
        }
        double SQRT_MAX = Math.sqrt(Double.MAX_VALUE);
        Range[] out = new Range[4];
        out[0] = new Range(ranges[0].getStart(), ranges[0].getEnd());
        out[1] = new Range(ranges[1].getStart(), ranges[1].getEnd());
        double x4s = Math.max(-SQRT_MAX, ranges[3].getStart());
        double x4e = Math.min(SQRT_MAX, ranges[3].getEnd());
        if (x4s > x4e) { throw new IllegalArgumentException("No valid subrange for x4 within overflow-safe bounds."); }
        out[3] = new Range(x4s, x4e);
        double x3s = Math.max(0.0, ranges[2].getStart());
        double x3e = ranges[2].getEnd();
        if (x3s > x3e) { throw new IllegalArgumentException("No valid subrange for x3 within domain constraints (x3 >= 0)."); }
        double maxAbsX4 = Math.max(Math.abs(x4s), Math.abs(x4e));
        if (maxAbsX4 > 0) {
            double limit = Double.MAX_VALUE / (maxAbsX4 * maxAbsX4);
            double maxX3Allowed = limit * limit;
            if (Double.isFinite(maxX3Allowed)) {
                x3e = Math.min(x3e, maxX3Allowed);
                if (x3s > x3e) {
                    throw new IllegalArgumentException("No valid subrange for x3 within overflow-safe bounds.");
                }
            }
        }
        out[2] = new Range(x3s, x3e);
        return out;
    }
}
