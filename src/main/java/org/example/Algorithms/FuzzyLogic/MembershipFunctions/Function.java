package org.example.Algorithms.FuzzyLogic.MembershipFunctions;
import java.util.List;

public interface Function {
    double fuzzify(double x);

    double getCentroid();

    List<Double> getMembership();
    double getMaxPoint();
    String getName();
}
