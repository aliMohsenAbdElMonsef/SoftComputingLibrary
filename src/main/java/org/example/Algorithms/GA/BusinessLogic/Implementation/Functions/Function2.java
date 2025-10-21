package org.example.Algorithms.GA.BusinessLogic.Implementation.Functions;

import org.example.Algorithms.GA.BusinessLogic.Contracts.Functions.Functions;
import org.example.Algorithms.GA.BusinessLogic.Implementation.Chromosomes.Range;

public class Function2 implements Functions{
    @Override
    public double method(double x1, double x2, double x3, double x4) {
        return Math.sin(Math.toRadians(x1 * x2)) + Math.cos(Math.toRadians(x3)) + .5 * (double) Math.pow(x4, 2);
    }
    @Override
    public Range[] validateInput(Range[] ranges) {
        if (ranges == null || ranges.length != 4) {
            throw new IllegalArgumentException("ranges must be length 4");
        }
        double SQRT_MAX = Math.sqrt(Double.MAX_VALUE);
        double BOUND = SQRT_MAX;
        Range[] out = new Range[4];
        double x1s = Math.max(-BOUND, ranges[0].getStart());
        double x1e = Math.min(BOUND, ranges[0].getEnd());
        if (x1s > x1e) { throw new IllegalArgumentException("No valid subrange for x1 within overflow-safe bounds."); }
        out[0] = new Range(x1s, x1e);
        double x2s = Math.max(-BOUND, ranges[1].getStart());
        double x2e = Math.min(BOUND, ranges[1].getEnd());
        if (x2s > x2e) { throw new IllegalArgumentException("No valid subrange for x2 within overflow-safe bounds."); }
        out[1] = new Range(x2s, x2e);
        out[2] = new Range(ranges[2].getStart(), ranges[2].getEnd());
        double x4s = Math.max(-SQRT_MAX, ranges[3].getStart());
        double x4e = Math.min(SQRT_MAX, ranges[3].getEnd());
        if (x4s > x4e) { throw new IllegalArgumentException("No valid subrange for x4 within overflow-safe bounds."); }
        out[3] = new Range(x4s, x4e);
        return out;
    }
}