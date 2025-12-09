package org.example.Algorithms.FuzzyLogic.RulesManagement;

import org.example.Algorithms.FuzzyLogic.LinguisticVariable.Linguistic_Variable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class RulesParser {
    protected static String orType;
    protected static String andType;

    public RulesParser(String orType, String andType) {
        RulesParser.orType = orType;
        RulesParser.andType = andType;
    }

    public abstract Rule parseRule(String rule, Map<String, Linguistic_Variable> variables);

    protected static List<String> extractConditions(String text) {
        List<String> list = new ArrayList<>();

        Matcher m = Pattern.compile("(?i)(NOT\\s+)?\\w+\\s+IS\\s+(NOT\\s+)?\\w+").matcher(text);
        while (m.find())
            list.add(m.group().trim());
        return list;
    }

    protected static List<String> extractOperators(String text) {
        List<String> ops = new ArrayList<>();
        Matcher m = Pattern.compile("(?i)\\s+(AND|OR)\\s+").matcher(text);
        while (m.find())
            ops.add(m.group(1).toUpperCase());
        return ops;
    }

    protected static Portion parsePortion(String text, Map<String, Linguistic_Variable> vars) {
        boolean isNot = false;
        String trimmedText = text.trim();
        

        if (trimmedText.toUpperCase().startsWith("NOT")) {
            isNot = true;
            trimmedText = trimmedText.substring(3).trim();
        }

        String[] parts = trimmedText.split("(?i)\\s+IS\\s+");
        if (parts.length != 2)
            throw new RuntimeException("Invalid portion syntax: " + text);

        String varName = parts[0].trim();
        String funcName = parts[1].trim();


        if (funcName.toUpperCase().startsWith("NOT ")) {
            if (isNot) {
                isNot = !isNot;
            } else {
                isNot = true;
            }
            funcName = funcName.substring(3).trim();
        }

        Linguistic_Variable var = vars.get(varName);
        if (var == null)
            throw new RuntimeException("Unknown variable: " + varName);

        if (var.getFunction(funcName) == null)
            throw new RuntimeException("Unknown fuzzy set: '" + funcName + "' for variable '" + varName + "'");

        return new Portion(var, funcName, isNot);
    }
}
