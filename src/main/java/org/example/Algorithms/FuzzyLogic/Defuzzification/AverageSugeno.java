package org.example.Algorithms.FuzzyLogic.Defuzzification;

import org.example.Algorithms.FuzzyLogic.LinguisticVariable.Linguistic_Variable;

import java.util.List;
import java.util.Map;

public class AverageSugeno implements defuzzify
{

    @Override
    public double evaluate(Linguistic_Variable variable, List<Map.Entry<String, Double>> aggregated)
    {
        double sumAlphaTimesZ = 0;
        double sumAlpha = 0;
        for (Map.Entry<String, Double> entry : aggregated) {
            double alpha = Double.parseDouble(entry.getKey());
            double zValue = entry.getValue();
            sumAlphaTimesZ += alpha * zValue;
            sumAlpha += alpha;
        }
        return (sumAlpha == 0) ? 0 : sumAlphaTimesZ / sumAlpha;
    }
}
