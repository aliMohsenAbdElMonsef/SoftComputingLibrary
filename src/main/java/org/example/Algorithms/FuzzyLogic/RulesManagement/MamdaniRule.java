package org.example.FuzzyLogic.RulesManagement;

import org.example.FuzzyLogic.Operations.Operation;

import java.util.List;
import java.util.Map;

public class MamdaniRule extends Rule {
    private Portion consequent;
    public MamdaniRule(List<Portion> antecedents, List<Operation> antecedentOps, Portion consequent) {
        super(antecedents, antecedentOps);
        this.consequent = consequent;
    }
    @Override
    public double getOutput(Map<String, Double> inputs) {
        return evaluateAntecedent(inputs);
    }
    public Portion getConsequent() {
        return consequent;
    }
}
