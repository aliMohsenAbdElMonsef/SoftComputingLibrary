package org.example.Algorithms.FuzzyLogic.RulesManagement;

import org.example.Algorithms.FuzzyLogic.Operations.Operation;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class SugenoRule extends Rule {
    private final Function<Map<String, Double>, Double> consequentFunction;

    public SugenoRule(List<Portion> antecedents, List<Operation> antecedentOps,
                      Function<Map<String, Double>, Double> function) {
        super(antecedents, antecedentOps);
        this.consequentFunction = function;
    }

    @Override
    public double getOutput(Map<String, Double> inputs) {
        return consequentFunction.apply(inputs);
    }
}
