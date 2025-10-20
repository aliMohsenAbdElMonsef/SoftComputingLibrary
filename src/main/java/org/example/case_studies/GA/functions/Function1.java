package org.example.case_studies.GA.functions;

import org.example.Algorithms.GA.chromosomes.Range;

public class Function1 implements  Functions{

    @Override
    public double method(double x1, double x2, double x3, double x4) {
        return Math.sin(Math.toRadians(x1)) + Math.cos(Math.toRadians(x2)) + Math.pow(x3,2) - Math.sqrt(x4);
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
        double x3s = Math.max(-SQRT_MAX, ranges[2].getStart());
        double x3e = Math.min(SQRT_MAX, ranges[2].getEnd());
        if (x3s > x3e) {
            throw new IllegalArgumentException("No valid subrange for x3 within parent range after overflow constraints.");
        }
        out[2] = new Range(x3s, x3e);
        double x4s = Math.max(0.0, ranges[3].getStart());
        double x4e = ranges[3].getEnd();
        if (x4s > x4e) {
            throw new IllegalArgumentException("No valid subrange for x4 within parent range after domain constraints (x4 >= 0).");
        }
        out[3] = new Range(x4s, x4e);
        return out;
    }
}
