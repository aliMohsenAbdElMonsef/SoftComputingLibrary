package org.example.FuzzyLogic.Operations;

public class ANDFactory {
    public static AND create(String type) {
        return switch (type.toLowerCase()) {
            case "min" -> new ANDMin();
            case "product" -> new ANDProduct();
            default -> throw new IllegalArgumentException("Unknown AND type: " + type);
        };
    }
}
