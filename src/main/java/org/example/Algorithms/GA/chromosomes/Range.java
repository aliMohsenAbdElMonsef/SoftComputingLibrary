package org.example.Algorithms.GA.chromosomes;

public class Range {
    private double start = 0.0;
    private double end = 5.0;

    public Range(double start, double end) {
        this.start = start;
        this.end = end;
    }

    public double getStart() {
        return start;
    }

    public double getEnd() {
        return end;
    }
    public static boolean checkRange(double st, double end)
    {
        return (st <= end);
    }
}
