package org.example.Algorithms.NN.Training;

public class OptimizerFactory {

    public static Optimizer create(String type, double learningRate) {
        return switch (type.toLowerCase()) {
            case "sgd" -> new SGDOptimizer(learningRate);
            case "adam" -> new AdamOptimizer(learningRate);
            default -> throw new IllegalArgumentException(
                    "Unknown optimizer: " + type + ". Available: sgd, adam");
        };
    }

    public static Optimizer createDefault(double learningRate) {
        return new SGDOptimizer(learningRate);
    }
}
