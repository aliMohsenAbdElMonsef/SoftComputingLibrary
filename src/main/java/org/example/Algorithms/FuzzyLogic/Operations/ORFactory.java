package org.example.FuzzyLogic.Operations;

public class ORFactory {
    public static OR create(String type) {
        return switch (type.toLowerCase()) {
            case "max" -> new ORMax();
            case "sum" -> new ORSum();
            default -> throw new IllegalArgumentException("Unknown OR type: " + type);
        };
    }
}
