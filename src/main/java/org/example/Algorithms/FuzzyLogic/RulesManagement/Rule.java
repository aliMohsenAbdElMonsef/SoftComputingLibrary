package org.example.Algorithms.FuzzyLogic.RulesManagement;

import org.example.Algorithms.FuzzyLogic.Operations.Operation;

import java.util.List;
import java.util.Map;

public abstract class Rule {
    protected String Representation;
    protected List<Portion> antecedents;
    protected List<Operation> antecedentOps;
    protected boolean enabled = true;
    protected double weight = 1.0;
    public Rule(List<Portion> antecedents, List<Operation> antecedentOps) {
        this.antecedents = antecedents;
        this.antecedentOps = antecedentOps;
    }
    public boolean isEnabled() {
        return enabled;
    }
    public void setWeight(double weight) { this.weight = weight; }
    public double getWeight() { return weight; }
    public void setEnabled(boolean enabled) { this.enabled = enabled; }
    public double evaluateAntecedent(Map<String, Double> inputs) {
        if (!enabled) return 0;
        double result = antecedents.get(0).getMembership(inputs.get(antecedents.get(0).variable.getName()));
        for (int i = 1; i < antecedents.size(); i++) {
            double nextValue = antecedents.get(i).getMembership(inputs.get(antecedents.get(i).variable.getName()));
            result = antecedentOps.get(i - 1).apply(result, nextValue);
        }
        return result * weight;
    }
    public abstract double getOutput(Map<String, Double> inputs);
    @Override
    public String toString() { return Representation; }
}
