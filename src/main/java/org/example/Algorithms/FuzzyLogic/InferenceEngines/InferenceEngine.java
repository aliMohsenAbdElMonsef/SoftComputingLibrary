package org.example.Algorithms.FuzzyLogic.InferenceEngines;

import org.example.Algorithms.FuzzyLogic.Defuzzification.DefuzzifyType;
import org.example.Algorithms.FuzzyLogic.LinguisticVariable.Linguistic_Variable;

import java.util.List;
import java.util.Map;

public interface InferenceEngine
{
    List<Map.Entry<String, Double>> evaluate(Map<String, Double> inputs);
    double Defuzzify(Linguistic_Variable variable, List<Map.Entry<String, Double>> results, DefuzzifyType type) throws  Exception;
}
