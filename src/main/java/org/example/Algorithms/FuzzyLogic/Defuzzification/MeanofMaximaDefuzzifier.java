package org.example.Algorithms.FuzzyLogic.Defuzzification;

import org.example.Algorithms.FuzzyLogic.LinguisticVariable.Linguistic_Variable;
import org.example.Algorithms.FuzzyLogic.MembershipFunctions.Function;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MeanofMaximaDefuzzifier implements defuzzify{
    @Override
    public double evaluate(Linguistic_Variable variable, List<Map.Entry<String, Double>> aggregated) {
        double maxAlpha = 0;
        for (Map.Entry<String, Double> entry : aggregated)
            maxAlpha = Math.max(maxAlpha, entry.getValue());
        List<Double> maxima = new ArrayList<>();
        for (Map.Entry<String, Double> entry : aggregated) {
            if (entry.getValue() == maxAlpha) {
                Function mf = variable.getFunction(entry.getKey());
                double maxPoint = mf.getMaxPoint();
                maxima.add(maxPoint);
            }
        }
        if (maxima.isEmpty()) return 0;
        double sum = 0;
        for (double v : maxima) sum += v;

        return sum / maxima.size();
    }
}
