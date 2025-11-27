package org.example.FuzzyLogic.Operations;

public class OperationsFactory {
    public static Operation create(String category, String type) {
        return switch (category.toUpperCase()) {
            case "AND" -> ANDFactory.create(type);
            case "OR" -> ORFactory.create(type);
            default -> throw new IllegalArgumentException("Unknown operation category: " + category);
        };
    }
}
