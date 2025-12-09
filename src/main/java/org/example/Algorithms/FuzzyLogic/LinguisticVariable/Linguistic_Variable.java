package org.example.Algorithms.FuzzyLogic.LinguisticVariable;

import org.example.Algorithms.FuzzyLogic.MembershipFunctions.Function;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class Linguistic_Variable {
    String name;
    Vector<Function> functions;
    public Linguistic_Variable(String name) {
        this.name = name;
        functions = new Vector<>();
    }
    public String getName() {
        return name;
    }
    public Vector<Function> getFunctions() {
        return functions;
    }
    public Function getFunction(String name) {
        for (Function f : functions) {
            if (f.getName().equals(name))
                return f;
        }
        return null;
    }

    public void addFunction(Function function) {
        this.functions.add(function);
    }
    public Map<String, Double> fuzzify(double input) {
        Map<String, Double> result = new HashMap<>();
        for (Function f : functions) {
            result.put(f.getName(), f.fuzzify(input));
        }
        return result;
    }
}
