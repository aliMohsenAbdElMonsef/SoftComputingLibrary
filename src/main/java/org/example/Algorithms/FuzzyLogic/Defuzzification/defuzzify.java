package org.example.Algorithms.FuzzyLogic.Defuzzification;

import org.example.Algorithms.FuzzyLogic.LinguisticVariable.Linguistic_Variable;

import java.util.List;
import java.util.Map;

public interface defuzzify
{
    double evaluate(Linguistic_Variable variable, List<Map.Entry<String, Double>> aggregated);
}

