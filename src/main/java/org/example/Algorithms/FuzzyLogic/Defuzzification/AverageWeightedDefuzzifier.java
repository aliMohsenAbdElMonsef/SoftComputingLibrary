package org.example.Algorithms.FuzzyLogic.Defuzzification;

import org.example.Algorithms.FuzzyLogic.LinguisticVariable.Linguistic_Variable;
import org.example.Algorithms.FuzzyLogic.MembershipFunctions.Function;

import java.util.List;
import java.util.Map;

public class AverageWeightedDefuzzifier implements defuzzify{
    @Override
    public double evaluate(Linguistic_Variable variable, List<Map.Entry<String, Double>> aggregated) {
        double numerator = 0;
        double denominator = 0;
        for (Map.Entry<String, Double> entry : aggregated) {
            String term = entry.getKey();
            double alpha = entry.getValue();
            Function mf = variable.getFunction(term);
            double centroid = mf.getCentroid();
            double mfvalue = entry.getValue();
            numerator += centroid * mfvalue;
            denominator += mfvalue;
        }
        return denominator == 0 ? 0 : numerator / denominator;
    }
}
