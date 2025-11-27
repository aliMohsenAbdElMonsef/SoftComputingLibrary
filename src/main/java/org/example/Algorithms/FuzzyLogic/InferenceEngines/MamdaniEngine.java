package org.example.FuzzyLogic.InferenceEngines;
import org.example.FuzzyLogic.Defuzzification.DefuzziferFactory;
import org.example.FuzzyLogic.Defuzzification.DefuzzifyType;
import org.example.FuzzyLogic.Defuzzification.defuzzify;
import org.example.FuzzyLogic.LinguisticVariable.Linguistic_Variable;
import org.example.FuzzyLogic.RulesManagement.MamdaniRule;
import org.example.FuzzyLogic.RulesManagement.Portion;
import org.example.FuzzyLogic.RulesManagement.Rule;
import org.example.FuzzyLogic.RulesManagement.RuleManager;

import java.util.*;

public class MamdaniEngine implements InferenceEngine{
    private final RuleManager ruleManager;
    private DefuzziferFactory defuzzifierFactory;
    public MamdaniEngine(RuleManager _ruleManager) {
        this.ruleManager = _ruleManager;
    }

    @Override
    public List<Map.Entry<String, Double>> evaluate(Map<String, Double> inputs) {
        List<Map.Entry<String, Double>> aggregated = new ArrayList<>();
        for (Rule r : ruleManager.getAllRules()) {
            if (!(r instanceof MamdaniRule) || !r.isEnabled())
                continue;
            MamdaniRule rule = (MamdaniRule) r;
            double alpha = rule.evaluateAntecedent(inputs);
            if (alpha == 0) continue;
            Portion c = rule.getConsequent();
            String termName = c.functionName;
            aggregated.add(new AbstractMap.SimpleEntry<>(termName, alpha));
        }
        return aggregated;
    }



    @Override
    public double Defuzzify(Linguistic_Variable variable, List<Map.Entry<String, Double>> results, DefuzzifyType type)
    {
        defuzzifierFactory = new DefuzziferFactory(type);
        defuzzify defuzzifer = defuzzifierFactory.defuzzify();
        return defuzzifer.evaluate(variable,results);
    }
}
