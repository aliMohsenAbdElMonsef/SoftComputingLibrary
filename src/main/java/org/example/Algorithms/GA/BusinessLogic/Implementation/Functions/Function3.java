package org.example.Algorithms.GA.BusinessLogic.Implementation.Functions;

import org.example.Algorithms.GA.BusinessLogic.Contracts.Functions.Functions;
import org.example.Algorithms.GA.BusinessLogic.Implementation.Chromosomes.Range;

public class Function3 implements Functions{
    @Override
    public double method(double x1, double x2, double x3, double x4) {
        return x1 + Math.pow(x2, 2) + Math.pow(x3, 3) + Math.pow(x4, 4);
    }
    @Override
    public Range[] validateInput(Range[] ranges) {
        if (ranges == null || ranges.length != 4) {
            throw new IllegalArgumentException("ranges must be length 4");
        }
        double SQRT_MAX = Math.sqrt(Double.MAX_VALUE);
        double CBRT_MAX = Math.cbrt(Double.MAX_VALUE);
        double FOURTH_MAX = Math.pow(Double.MAX_VALUE, 0.25);
        Range[] out = new Range[4];
        out[0] = new Range(ranges[0].getStart(), ranges[0].getEnd());
        double x2s = Math.max(-SQRT_MAX, ranges[1].getStart());
        double x2e = Math.min(SQRT_MAX, ranges[1].getEnd());
        if (x2s > x2e) { throw new IllegalArgumentException("No valid subrange for x2 within overflow-safe bounds."); }
        out[1] = new Range(x2s, x2e);
        double x3s = Math.max(-CBRT_MAX, ranges[2].getStart());
        double x3e = Math.min(CBRT_MAX, ranges[2].getEnd());
        if (x3s > x3e) { throw new IllegalArgumentException("No valid subrange for x3 within overflow-safe bounds."); }
        out[2] = new Range(x3s, x3e);
        double x4s = Math.max(-FOURTH_MAX, ranges[3].getStart());
        double x4e = Math.min(FOURTH_MAX, ranges[3].getEnd());
        if (x4s > x4e) { throw new IllegalArgumentException("No valid subrange for x4 within overflow-safe bounds."); }
        out[3] = new Range(x4s, x4e);
        return out;
    }
}