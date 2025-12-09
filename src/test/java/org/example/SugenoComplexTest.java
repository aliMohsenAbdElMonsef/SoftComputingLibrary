package org.example;

import org.example.Algorithms.FuzzyLogic.Defuzzification.DefuzzifyType;
import org.example.Algorithms.FuzzyLogic.InferenceEngines.SugenoEngine;
import org.example.Algorithms.FuzzyLogic.LinguisticVariable.Linguistic_Variable;
import org.example.Algorithms.FuzzyLogic.MembershipFunctions.TriangularFunction;
import org.example.Algorithms.FuzzyLogic.RulesManagement.RuleManager;
import org.example.Algorithms.FuzzyLogic.RulesManagement.SugenoParser;

import java.util.HashMap;
import java.util.Map;

public class SugenoComplexTest {
    public static void main(String[] args) {
        try {
            System.out.println("=== Sugeno Complex Logic Test (AND, OR, NOT) ===");


            Map<String, Linguistic_Variable> variables = new HashMap<>();

            Linguistic_Variable inputX = new Linguistic_Variable("X");
            inputX.addFunction(new TriangularFunction("Small", 0, 0, 5));
            inputX.addFunction(new TriangularFunction("Large", 5, 10, 10));
            variables.put("X", inputX);

            Linguistic_Variable inputY = new Linguistic_Variable("Y");
            inputY.addFunction(new TriangularFunction("Small", 0, 0, 5));
            inputY.addFunction(new TriangularFunction("Large", 5, 10, 10));
            variables.put("Y", inputY);


            
            Linguistic_Variable outputZ = new Linguistic_Variable("Z"); 
            variables.put("Z", outputZ);


            RuleManager ruleManager = new RuleManager();
            SugenoParser parser = new SugenoParser("max", "min");
            SugenoEngine engine = new SugenoEngine(ruleManager);



            ruleManager.addRule("IF X is Small AND Y is Small THEN 10", variables, parser);


            ruleManager.addRule("IF X is Large OR Y is Large THEN X + Y", variables, parser);
            

            ruleManager.addRule("IF X is NOT Small THEN 2*X", variables, parser);


            Map<String, Double> inputs = new HashMap<>();
            inputs.put("X", 6.0);

            
            inputs.put("Y", 2.0);
            
            System.out.println("\nInputs: " + inputs);




            var results = engine.evaluate(inputs);
            

            
            double crisp = engine.Defuzzify(outputZ, results, DefuzzifyType.SUGENO_AVERAGE);
            System.out.println("\nCrisp Output (Z): " + crisp);

            if (Math.abs(crisp - 11.333) < 0.1) {
                System.out.println("SUCCESS: Output is consistent with expected Sugeno logic.");
            } else {
                System.out.println("WARNING: Output " + crisp + " deviates from expected ~11.33");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
