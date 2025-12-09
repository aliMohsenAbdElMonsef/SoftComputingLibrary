package org.example;

import org.example.Algorithms.FuzzyLogic.Defuzzification.DefuzzifyType;
import org.example.Algorithms.FuzzyLogic.InferenceEngines.MamdaniEngine;
import org.example.Algorithms.FuzzyLogic.LinguisticVariable.Linguistic_Variable;
import org.example.Algorithms.FuzzyLogic.MembershipFunctions.TrapezoidalFunction;
import org.example.Algorithms.FuzzyLogic.MembershipFunctions.TriangularFunction;
import org.example.Algorithms.FuzzyLogic.RulesManagement.MamdaniParser;
import org.example.Algorithms.FuzzyLogic.RulesManagement.RuleManager;

import java.util.HashMap;
import java.util.Map;

public class ComplexFuzzyTest {
    public static void main(String[] args) {
        try {
            System.out.println("=== Complex Fuzzy Logic Test (AND, OR, NOT) ===");


            Map<String, Linguistic_Variable> variables = new HashMap<>();


            Linguistic_Variable funding = new Linguistic_Variable("ProjectFunding");
            funding.addFunction(new TrapezoidalFunction("Low", 0, 0, 20, 40));
            funding.addFunction(new TriangularFunction("Medium", 30, 50, 70));
            funding.addFunction(new TrapezoidalFunction("High", 60, 80, 100, 100));
            variables.put("ProjectFunding", funding);


            Linguistic_Variable experience = new Linguistic_Variable("TeamExperience");
            experience.addFunction(new TrapezoidalFunction("Junior", 0, 0, 2, 4));
            experience.addFunction(new TriangularFunction("Intermediate", 3, 5, 7));
            experience.addFunction(new TrapezoidalFunction("Senior", 6, 8, 10, 10));
            variables.put("TeamExperience", experience);


            Linguistic_Variable risk = new Linguistic_Variable("RiskFactor");
            risk.addFunction(new TrapezoidalFunction("Low", 0, 0, 20, 40));
            risk.addFunction(new TrapezoidalFunction("High", 60, 80, 100, 100));
            variables.put("RiskFactor", risk);


            Linguistic_Variable success = new Linguistic_Variable("SuccessProbability");
            success.addFunction(new TrapezoidalFunction("Low", 0, 0, 30, 50));
            success.addFunction(new TriangularFunction("Medium", 40, 60, 80));
            success.addFunction(new TrapezoidalFunction("High", 70, 90, 100, 100));
            variables.put("SuccessProbability", success);


            RuleManager ruleManager = new RuleManager();

            MamdaniParser parser = new MamdaniParser("max", "min");
            MamdaniEngine engine = new MamdaniEngine(ruleManager);



            ruleManager.addRule("IF ProjectFunding IS High OR TeamExperience IS Senior THEN SuccessProbability IS High", variables, parser);
            

            ruleManager.addRule("IF ProjectFunding IS Low AND TeamExperience IS Junior THEN SuccessProbability IS Low", variables, parser);


            ruleManager.addRule("IF RiskFactor IS NOT Low THEN SuccessProbability IS Low", variables, parser);


            ruleManager.addRule("IF ProjectFunding IS Medium AND TeamExperience IS Intermediate OR RiskFactor IS Low THEN SuccessProbability IS Medium", variables, parser);


            Map<String, Double> inputs = new HashMap<>();
            inputs.put("ProjectFunding", 50.0);
            inputs.put("TeamExperience", 5.0);
            inputs.put("RiskFactor", 10.0);

            System.out.println("\nInputs: " + inputs);


            var results = engine.evaluate(inputs);

            System.out.println("\nFuzzy Results:");
            for (var entry : results) {
                System.out.println("  " + entry.getKey() + ": " + entry.getValue());
            }


            double crisp = engine.Defuzzify(success, results, DefuzzifyType.WEIGHTED_AVERAGE);
            System.out.println("\nCrisp Output (SuccessProbability): " + crisp);



            if (Math.abs(crisp - 60.0) < 5.0) {
                System.out.println("SUCCESS: Output is consistent with expected logic (dominated by Medium).");
            } else {
                System.out.println("WARNING: Output " + crisp + " deviates from expected ~60.0");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
