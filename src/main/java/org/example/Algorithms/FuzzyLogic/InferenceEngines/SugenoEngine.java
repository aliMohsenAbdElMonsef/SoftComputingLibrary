package org.example.FuzzyLogic.InferenceEngines;

import org.example.FuzzyLogic.Defuzzification.DefuzziferFactory;
import org.example.FuzzyLogic.Defuzzification.DefuzzifyType;
import org.example.FuzzyLogic.Defuzzification.defuzzify;
import org.example.FuzzyLogic.LinguisticVariable.Linguistic_Variable;
import org.example.FuzzyLogic.RulesManagement.Rule;
import org.example.FuzzyLogic.RulesManagement.RuleManager;
import org.example.FuzzyLogic.RulesManagement.SugenoRule;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SugenoEngine implements  InferenceEngine{
    private final RuleManager ruleManager;
    private DefuzziferFactory defuzzifierFactory;
    public SugenoEngine(RuleManager ruleManager) {
        this.ruleManager = ruleManager;
    }
    @Override
    public List<Map.Entry<String, Double>> evaluate(Map<String, Double> inputs) {
        List<Map.Entry<String, Double>> results = new ArrayList<>();
        for (Rule r : ruleManager.getAllRules()) {
            if (!(r instanceof SugenoRule) || !r.isEnabled())
                continue;
            SugenoRule rule = (SugenoRule) r;
            double alpha = rule.evaluateAntecedent(inputs);
            if (alpha == 0) continue;
            double z = rule.getOutput(inputs);
            results.add(new AbstractMap.SimpleEntry<>(String.valueOf(alpha), z));
        }
        return results;
    }

    @Override
    public double Defuzzify(Linguistic_Variable variable, List<Map.Entry<String, Double>> results, DefuzzifyType type)
    {
        defuzzifierFactory = new DefuzziferFactory(type);
        defuzzify defuzzifer = defuzzifierFactory.defuzzify();
        return defuzzifer.evaluate(variable,results);
    }
}

