package org.example.Algorithms.NN.DataHandling;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoricalEncoder {
    private final Map<String, Double> labelToCode = new HashMap<>();
    private final Map<Double, String> codeToLabel = new HashMap<>();
    private double currentCode = 0.0;

    public void fit(List<String> data) {
        for (String item : data) {
            if (!labelToCode.containsKey(item)) {
                labelToCode.put(item, currentCode);
                codeToLabel.put(currentCode, item);
                currentCode++;
            }
        }
    }

    public double transform(String item) {
        if (!labelToCode.containsKey(item)) {
            throw new IllegalArgumentException("Unseen label: " + item);
        }
        return labelToCode.get(item);
    }

    public String inverseTransform(double code) {
        return codeToLabel.get(code);
    }

    public Map<String, Double> getMapping() {
        return labelToCode;
    }
}
