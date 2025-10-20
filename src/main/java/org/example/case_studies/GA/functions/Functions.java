package org.example.case_studies.GA.functions;

import org.example.Algorithms.GA.chromosomes.Range;

public interface Functions {
    public abstract double method(double x1, double x2, double x3, double x4);
    public abstract Range[] validateInput(Range[] ranges);
}
