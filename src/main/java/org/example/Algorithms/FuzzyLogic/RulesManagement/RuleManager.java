package org.example.Algorithms.FuzzyLogic.RulesManagement;

import org.example.Algorithms.FuzzyLogic.LinguisticVariable.Linguistic_Variable;
import org.example.Algorithms.FuzzyLogic.RulesManagement.Rule;

import java.util.*;

public class RuleManager {

    private final Map<Integer, Rule> rules;
    private int nextId;
    public RuleManager()
    {
        rules = new HashMap<>();
        nextId = 1;
    }
    public int addRule(Rule rule)
    {
        int id = nextId++;
        rules.put(id, rule);
        return id;
    }

    public Map<Integer, Double> evaluateAll(Map<String, Double> inputs) {
        Map<Integer, Double> outputs = new HashMap<>();
        for (Map.Entry<Integer, Rule> entry : rules.entrySet()) {
            Rule r = entry.getValue();
            if (r.isEnabled()) {
                double result = r.getOutput(inputs);
                outputs.put(entry.getKey(), result);
            }
        }
        return outputs;
    }

    public int addRule(String ruleText, Map<String, Linguistic_Variable> variables, RulesParser parser) {
        Rule rule = parser.parseRule(ruleText, variables);
        return addRule(rule);
    }

    public Rule getRule(int id)
    {
        return rules.get(id);
    }

    public List<Rule> getAllRules()
    {
        return new ArrayList<>(rules.values());
    }

    public boolean removeRule(int id)
    {
        return rules.remove(id) != null;
    }

    private boolean UpdateRuleEnable(int id, boolean enabled)
    {
        Rule r = rules.get(id);
        if (r == null) return false;
        r.setEnabled(enabled);
        return true;
    }

    private boolean updateRuleWeight(int id, double weight)
    {
        Rule r = rules.get(id);
        if (r == null) return false;
        r.setWeight(weight);
        return true;
    }
    private boolean updateRuleString(int id, String newRuleText, Map<String, Linguistic_Variable> variables, RulesParser parser) {
        Rule oldRule = rules.get(id);
        if (oldRule == null) return false;
        Rule newRule = parser.parseRule(newRuleText, variables);
        newRule.setEnabled(oldRule.isEnabled());
        newRule.setWeight(oldRule.getWeight());
        rules.put(id, newRule);
        return true;
    }
    public boolean updateRule(Map<String,String> input, Map<String, Linguistic_Variable> variables, RulesParser parser)
    {
        int ruleId = Integer.parseInt(input.get("ID"));
        String ruleText = input.get("RuleText");
        boolean enabled = Boolean.parseBoolean(input.get("Enabled"));
        double weight = Double.parseDouble(input.get("Weight"));
        updateRuleString(ruleId, ruleText, variables, parser);
        updateRuleWeight(ruleId, weight);
        UpdateRuleEnable(ruleId, enabled);
        return true;
    }
    public String describeRule(int id) {
        Rule r = rules.get(id);
        if (r == null) return "Rule not found";
        return "Rule " + id + ": " + r.toString();
    }
    public void printAllRules()
    {
        for (Map.Entry<Integer, Rule> entry : rules.entrySet())
        {
            System.out.println(describeRule(entry.getKey()));
        }
    }
    public List<Rule> exportRules()
    {
        return new ArrayList<>(rules.values());
    }
}
