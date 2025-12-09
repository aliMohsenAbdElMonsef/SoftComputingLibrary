package org.example.Algorithms.FuzzyLogic.RulesManagement;

import org.example.Algorithms.FuzzyLogic.LinguisticVariable.Linguistic_Variable;

public class Portion {
    public boolean isNot;
    public Linguistic_Variable variable;
    public String functionName;

    public Portion(Linguistic_Variable variable, String functionName, boolean isNot) {
        this.variable = variable;
        this.functionName = functionName;
        this.isNot = isNot;
    }

    public double getMembership(double x) {
        double mu = variable.fuzzify(x).get(functionName);
        return isNot ? (1 - mu) : mu;
    }
}
