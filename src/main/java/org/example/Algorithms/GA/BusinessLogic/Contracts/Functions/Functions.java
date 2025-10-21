package org.example.Algorithms.GA.BusinessLogic.Contracts.Functions;

import org.example.Algorithms.GA.BusinessLogic.Implementation.Chromosomes.Range;

public interface Functions {
    public abstract double method(double x1, double x2, double x3, double x4);
    public abstract Range[] validateInput(Range[] ranges);
}
