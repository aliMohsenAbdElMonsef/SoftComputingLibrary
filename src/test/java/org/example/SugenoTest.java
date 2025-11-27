package org.example;

import org.example.FuzzyLogic.LinguisticVariable.Linguistic_Variable;
import org.example.FuzzyLogic.RulesManagement.Rule;
import org.example.FuzzyLogic.RulesManagement.SugenoParser;
import org.example.FuzzyLogic.RulesManagement.SugenoRule;

import java.util.HashMap;
import java.util.Map;

public class SugenoTest {
    public static void main(String[] args) {
        try {
            SugenoParser parser = new SugenoParser("max", "min");
            Map<String, Linguistic_Variable> variables = new HashMap<>();
            Linguistic_Variable var1 = new Linguistic_Variable("Var1");
            var1.addFunction(new org.example.FuzzyLogic.MembershipFunctions.TriangularFunction("Low", 0, 0, 10));
            variables.put("Var1", var1);

            String ruleText = "IF Var1 IS Low THEN x + 2*y - z + 5";
            Rule rule = parser.parseRule(ruleText, variables);

            if (rule instanceof SugenoRule) {
                SugenoRule sr = (SugenoRule) rule;
                Map<String, Double> inputs = new HashMap<>();
                inputs.put("x", 10.0);
                inputs.put("y", 2.0);
                inputs.put("z", 3.0);
                double output = sr.getOutput(inputs);
                System.out.println("Output: " + output);
                
                if (Math.abs(output - 16.0) < 0.001) {
                    System.out.println("SUCCESS: Sugeno formula parsed correctly.");
                } else {
                    System.err.println("FAILED: Expected 16.0, got " + output);
                    System.exit(1);
                }
            } else {
                System.err.println("FAILED: Rule is not SugenoRule");
                System.exit(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
