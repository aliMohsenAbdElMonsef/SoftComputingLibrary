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

public class MamdaniTest {
    public static void main(String[] args) {
        try {
            Map<String, Linguistic_Variable> variables = new HashMap<>();

            Linguistic_Variable temp = new Linguistic_Variable("Temp");
            temp.addFunction(new TrapezoidalFunction("Freezing", 0, 0, 30, 50));
            temp.addFunction(new TriangularFunction("Cool", 30, 50, 70));
            temp.addFunction(new TriangularFunction("Warm", 50, 70, 90));
            temp.addFunction(new TrapezoidalFunction("Hot", 70, 90, 110, 110));
            variables.put("Temp", temp);

            Linguistic_Variable cover = new Linguistic_Variable("Cover");
            cover.addFunction(new TrapezoidalFunction("Sunny", 0, 0, 20, 40));
            cover.addFunction(new TriangularFunction("Cloudy", 20, 50, 80));
            cover.addFunction(new TrapezoidalFunction("Overcast", 60, 80, 100, 100));
            variables.put("Cover", cover);

            Linguistic_Variable speed = new Linguistic_Variable("Speed");
            speed.addFunction(new TrapezoidalFunction("Slow", 0, 0, 25, 75));
            speed.addFunction(new TrapezoidalFunction("Fast", 25, 75, 100, 100));
            variables.put("Speed", speed);

            RuleManager ruleManager = new RuleManager();
            MamdaniParser parser = new MamdaniParser("max", "min");
            MamdaniEngine engine = new MamdaniEngine(ruleManager);

            ruleManager.addRule("IF Cover is Sunny and Temp is Warm THEN Speed is Fast", variables, parser);
            ruleManager.addRule("IF Cover is Cloudy and Temp is Cool THEN Speed is Slow", variables, parser);

            Map<String, Double> inputs = new HashMap<>();
            inputs.put("Temp", 65.0);
            inputs.put("Cover", 25.0);

            System.out.println("Running Mamdani inference with inputs: " + inputs);
            var results = engine.evaluate(inputs);

            System.out.println("Fuzzy Results:");
            for (var entry : results) {
                System.out.println("  " + entry.getKey() + ": " + entry.getValue());
            }

            double crisp = engine.Defuzzify(speed, results, DefuzzifyType.WEIGHTED_AVERAGE);
            System.out.println("Final Output (Speed): " + crisp);

            if (Math.abs(crisp - 65.909) < 0.01) {
                System.out.println("SUCCESS: Mamdani inference produced expected result.");
            } else {
                System.err.println("WARNING: Expected ~65.91, got " + crisp);
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
