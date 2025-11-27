package org.example.Algorithms.FuzzyLogic;

import org.example.FuzzyLogic.Defuzzification.DefuzzifyType;
import org.example.FuzzyLogic.InferenceEngines.InferenceEngine;
import org.example.FuzzyLogic.InferenceEngines.MamdaniEngine;
import org.example.FuzzyLogic.InferenceEngines.SugenoEngine;
import org.example.FuzzyLogic.LinguisticVariable.Linguistic_Variable;
import org.example.FuzzyLogic.MembershipFunctions.Function;
import org.example.FuzzyLogic.MembershipFunctions.GaussianFunction;
import org.example.FuzzyLogic.MembershipFunctions.TrapezoidalFunction;
import org.example.FuzzyLogic.MembershipFunctions.TriangularFunction;
import org.example.FuzzyLogic.RulesManagement.MamdaniParser;
import org.example.FuzzyLogic.RulesManagement.RuleManager;
import org.example.FuzzyLogic.RulesManagement.RulesParser;
import org.example.FuzzyLogic.RulesManagement.SugenoParser;

import java.util.*;

public class FuzzyLogicCLI {
    private final Scanner scanner;
    private final Map<String, Linguistic_Variable> variables;
    private final RuleManager ruleManager;
    private RulesParser parser;
    private InferenceEngine engine;
    private boolean isMamdani = true;

    public FuzzyLogicCLI() {
        this.scanner = new Scanner(System.in);
        this.variables = new HashMap<>();
        this.ruleManager = new RuleManager();
    }

    public void run() {
        System.out.println("=== Fuzzy Logic System CLI ===");

        try {

            configureVariables();

            configureEngine();

            manageRules();

            DefuzzifyType defuzzifyType = configureDefuzzification();

            runSystem(defuzzifyType);

        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }

    private void configureVariables() {
        System.out.println("\n--- Linguistic Variables Configuration ---");
        int numVars = getValidInt("Enter the number of linguistic variables: ", 1, 100);

        for (int i = 0; i < numVars; i++) {
            System.out.println("\nConfiguring Variable #" + (i + 1));
            String name = getValidString("Enter variable name (unique): ");
            while (variables.containsKey(name)) {
                System.out.println("Variable name already exists. Try again.");
                name = getValidString("Enter variable name (unique): ");
            }

            Linguistic_Variable variable = new Linguistic_Variable(name);
            int numSets = getValidInt("Enter number of fuzzy sets for " + name + ": ", 1, 20);

            for (int j = 0; j < numSets; j++) {
                System.out.println("  Configuring Fuzzy Set #" + (j + 1));
                addFuzzySet(variable);
            }

            variables.put(name, variable);
        }
    }

    private void addFuzzySet(Linguistic_Variable variable) {
        String setName = getValidString("  Enter fuzzy set name (e.g., Low, High): ");

        System.out.println("  Select Fuzzy Set Type:");
        System.out.println("  1. Triangular");
        System.out.println("  2. Trapezoidal");
        System.out.println("  3. Gaussian");
        int typeChoice = getValidInt("  Choice: ", 1, 3);

        Function function = null;
        switch (typeChoice) {
            case 1:
                System.out.println("  Enter points (a < b < c):");
                double a = getValidDouble("  a: ");
                double b = getValidDouble("  b: ");
                double c = getValidDouble("  c: ");
                function = new TriangularFunction(setName, a, b, c);
                break;
            case 2:
                System.out.println("  Enter points (a < b < c < d):");
                double ta = getValidDouble("  a: ");
                double tb = getValidDouble("  b: ");
                double tc = getValidDouble("  c: ");
                double td = getValidDouble("  d: ");
                function = new TrapezoidalFunction(setName, ta, tb, tc, td);
                break;
            case 3:
                double mean = getValidDouble("  Enter Mean: ");
                double stdDev = getValidDouble("  Enter Standard Deviation: ");
                function = new GaussianFunction(setName, mean, stdDev);
                break;
        }
        variable.addFunction(function);
    }

    private void configureEngine() {
        System.out.println("\n--- Inference Engine Selection ---");
        System.out.println("1. Mamdani");
        System.out.println("2. Sugeno");
        int choice = getValidInt("Choice: ", 1, 2);
        isMamdani = (choice == 1);

        System.out.println("Select AND operator: 1. Min, 2. Product");
        String andType = (getValidInt("Choice: ", 1, 2) == 1) ? "min" : "product";

        System.out.println("Select OR operator: 1. Max, 2. Sum");
        String orType = (getValidInt("Choice: ", 1, 2) == 1) ? "max" : "sum";

        if (isMamdani) {
            parser = new MamdaniParser(orType, andType);
            engine = new MamdaniEngine(ruleManager);
        } else {
            parser = new SugenoParser(orType, andType);
            engine = new SugenoEngine(ruleManager);
        }
    }

    private void manageRules() {
        boolean managing = true;
        while (managing) {
            System.out.println("\n--- Rule Management ---");
            System.out.println("Current Rules:");
            ruleManager.printAllRules();

            System.out.println("\nOptions:");
            System.out.println("1. Add a new rule");
            System.out.println("2. Update an existing rule");
            System.out.println("3. Enable/Disable rule");
            System.out.println("4. Adjust rule weight");
            System.out.println("5. Finish managing rules");

            int choice = getValidInt("Choice: ", 1, 5);

            try {
                switch (choice) {
                    case 1:
                        System.out.println("Enter rule text (e.g., IF Var1 IS Low THEN Var2 IS High):");
                        String ruleText = scanner.nextLine().trim();
                        ruleManager.addRule(ruleText, variables, parser);
                        System.out.println("Rule added.");
                        break;
                    case 2:
                        int updateId = getValidInt("Enter Rule ID to update: ", 1, 1000);
                        System.out.println("Enter new rule text:");
                        String newText = scanner.nextLine().trim();
                        Map<String, String> updateParams = new HashMap<>();
                        updateParams.put("ID", String.valueOf(updateId));
                        updateParams.put("RuleText", newText);
                        updateParams.put("Enabled", "true");
                        updateParams.put("Weight", "1.0");
                        ruleManager.updateRule(updateParams, variables, parser);
                        System.out.println("Rule updated.");
                        break;
                    case 3:
                        int toggleId = getValidInt("Enter Rule ID: ", 1, 1000);
                        var rule = ruleManager.getRule(toggleId);
                        if (rule != null) {
                            boolean newState = !rule.isEnabled();
                            rule.setEnabled(newState);
                            System.out.println("Rule " + (newState ? "Enabled" : "Disabled"));
                        } else {
                            System.out.println("Rule not found.");
                        }
                        break;
                    case 4:
                        int weightId = getValidInt("Enter Rule ID: ", 1, 1000);
                        double weight = getValidDouble("Enter new weight (0.0 - 1.0): ");
                        var wRule = ruleManager.getRule(weightId);
                        if (wRule != null) {
                            wRule.setWeight(weight);
                            System.out.println("Weight updated.");
                        } else {
                            System.out.println("Rule not found.");
                        }
                        break;
                    case 5:
                        managing = false;
                        break;
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private DefuzzifyType configureDefuzzification() {
        System.out.println("\n--- Defuzzification Method Selection ---");
        if (isMamdani) {
            System.out.println("1. Weighted Average");
            System.out.println("2. Mean of Maxima");
            int choice = getValidInt("Choice: ", 1, 2);
            return (choice == 1) ? DefuzzifyType.WEIGHTED_AVERAGE : DefuzzifyType.MEAN_OF_MAXIMA;
        } else {
            System.out.println("Using Average Sugeno (Default for Sugeno)");
            return DefuzzifyType.SUGENO_AVERAGE;
        }
    }

    private void runSystem(DefuzzifyType defuzzifyType) {
        System.out.println("\n--- System Execution ---");
        Map<String, Double> inputs = new HashMap<>();

        System.out.println("Available variables: " + variables.keySet());
        String outputVarName = getValidString("Enter the name of the Output variable: ");
        while (!variables.containsKey(outputVarName)) {
            System.out.println("Unknown variable.");
            outputVarName = getValidString("Enter the name of the Output variable: ");
        }
        Linguistic_Variable outputVar = variables.get(outputVarName);

        for (String varName : variables.keySet()) {
            if (varName.equals(outputVarName))
                continue;
            double val = getValidDouble("Enter value for " + varName + ": ");
            inputs.put(varName, val);
        }

        try {
            System.out.println("Running inference...");
            List<Map.Entry<String, Double>> results = engine.evaluate(inputs);

            System.out.println("Fuzzy Results:");
            for (Map.Entry<String, Double> entry : results) {
                System.out.println("  " + entry.getKey() + ": " + entry.getValue());
            }

            double crisp = engine.Defuzzify(outputVar, results, defuzzifyType);
            System.out.println("Crisp Output (" + outputVarName + "): " + crisp);

        } catch (Exception e) {
            System.err.println("Error during execution: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private int getValidInt(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            try {
                int val = Integer.parseInt(input);
                if (val >= min && val <= max)
                    return val;
                System.out.println("Please enter a number between " + min + " and " + max + ".");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter an integer.");
            }
        }
    }

    private double getValidDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    private String getValidString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty())
                return input;
            System.out.println("Input cannot be empty.");
        }
    }
}
