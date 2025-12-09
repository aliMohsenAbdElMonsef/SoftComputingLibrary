package org.example.Algorithms.FuzzyLogic.RulesManagement;

import org.example.Algorithms.FuzzyLogic.LinguisticVariable.Linguistic_Variable;
import org.example.Algorithms.FuzzyLogic.Operations.Operation;
import org.example.Algorithms.FuzzyLogic.Operations.OperationsFactory;

import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SugenoParser extends RulesParser {

    public SugenoParser(String orType, String andType) {
        super(orType, andType);
    }

    private void validateFormula(String formula) {
        String f = formula.replaceAll("\\s+", "");
        Pattern invalidPattern = Pattern.compile("\\d+[a-zA-Z_]+");
        Matcher m = invalidPattern.matcher(f);
        if (m.find()) {
            throw new RuntimeException("Invalid formula: '" + m.group() + "'. "
                    + "Please use '*' between coefficient and variable, e.g., -2*temp instead of -2temp.");
        }
    }

    @Override
    public Rule parseRule(String ruleText, Map<String, Linguistic_Variable> variables) {
        String[] parts = ruleText.trim().split("(?i)THEN");
        if (parts.length != 2)
            throw new RuntimeException("Invalid rule syntax: " + ruleText);
        String antecedentStr = parts[0].replaceFirst("(?i)IF", "").trim();
        String consequentStr = parts[1].trim();
        List<String> conds = extractConditions(antecedentStr);
        List<String> ops = extractOperators(antecedentStr);
        List<Portion> antecedents = new ArrayList<>();
        for (String c : conds)
            antecedents.add(parsePortion(c, variables));
        List<Operation> operators = new ArrayList<>();
        for (String op : ops)
            operators.add(
                    OperationsFactory.create(op.equals("AND") ? "AND" : "OR", op.equals("AND") ? andType : orType));
        Function<Map<String, Double>, Double> func = (inputs) -> {
            double result = 0;
            String formula = consequentStr.replaceAll("\\s+", "");
            validateFormula(formula);
            Pattern termPattern = Pattern
                    .compile("([+-]?(\\d*(\\.\\d+)?)\\*[a-zA-Z_]+)|([+-]?[a-zA-Z_]+)|([+-]?\\d+(\\.\\d+)?)");
            Matcher matcher = termPattern.matcher(formula);
            while (matcher.find()) {
                String term = matcher.group();
                double termValue = 0;

                if (matcher.group(1) != null) {
                    String[] partsTerm = term.split("\\*");
                    double coeff = partsTerm[0].isEmpty() || partsTerm[0].equals("+") ? 1
                            : partsTerm[0].equals("-") ? -1 : Double.parseDouble(partsTerm[0]);
                    String var = partsTerm[1];
                    termValue = coeff * inputs.getOrDefault(var, 0.0);
                } else if (matcher.group(4) != null) {
                    String var = term;
                    double coeff = 1.0;
                    if (var.startsWith("-")) {
                        coeff = -1.0;
                        var = var.substring(1);
                    } else if (var.startsWith("+")) {
                        var = var.substring(1);
                    }
                    termValue = coeff * inputs.getOrDefault(var, 0.0);
                } else {
                    try {
                        termValue = Double.parseDouble(term);
                    } catch (NumberFormatException e) {

                    }
                }
                result += termValue;
            }
            return result;
        };
        SugenoRule r = new SugenoRule(antecedents, operators, func);
        r.Representation = ruleText;
        return r;
    }
}
