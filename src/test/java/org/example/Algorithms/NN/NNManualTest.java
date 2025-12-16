package org.example.Algorithms.NN;

import org.example.Algorithms.NN.Core.Layer;
import org.example.Algorithms.NN.Core.NeuralNetwork;
import org.example.Algorithms.NN.Core.NeuralNetworkBuilder;
import org.example.Algorithms.NN.DataHandling.Preproccesing.StandardScaler;
import org.example.Algorithms.NN.ActivationFunctions.Sigmoid;
import org.example.Algorithms.NN.WeightInitialization.RandomUniformInitializer;

public class NNManualTest {
    public static void main(String[] args) {
        try {
            testInputValidation_NullInput();
            testInputValidation_EmptyInput();
            testInputValidation_EmptyLayers();
            testInputValidation_WrongSize();
            testPreprocessing_StandardScaler();
            testForwardPass();
            System.out.println("ALL TESTS PASSED");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private static void testInputValidation_NullInput() {
        System.out.println("Running testInputValidation_NullInput...");
        NeuralNetwork nn = new NeuralNetwork();
        try {
            nn.forward(null);
            throw new RuntimeException("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            if (!"Input cannot be null or empty".equals(e.getMessage())) {
                throw new RuntimeException("Wrong message: " + e.getMessage());
            }
        }
    }

    private static void testInputValidation_EmptyInput() {
        System.out.println("Running testInputValidation_EmptyInput...");
        NeuralNetwork nn = new NeuralNetwork();
        try {
            nn.forward(new double[] {});
            throw new RuntimeException("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            if (!"Input cannot be null or empty".equals(e.getMessage())) {
                throw new RuntimeException("Wrong message: " + e.getMessage());
            }
        }
    }

    private static void testInputValidation_EmptyLayers() {
        System.out.println("Running testInputValidation_EmptyLayers...");
        NeuralNetwork nn = new NeuralNetwork();
        try {
            nn.forward(new double[] { 1.0 });
            throw new RuntimeException("Expected IllegalStateException");
        } catch (IllegalStateException e) {
            if (!"Neural Network has no layers".equals(e.getMessage())) {
                throw new RuntimeException("Wrong message: " + e.getMessage());
            }
        }
    }

    private static void testInputValidation_WrongSize() {
        System.out.println("Running testInputValidation_WrongSize...");
        NeuralNetworkBuilder builder = new NeuralNetworkBuilder().inputSize(2);
        builder.addLayer(2, new org.example.Algorithms.NN.ActivationFunctions.ReLU(),
                new org.example.Algorithms.NN.WeightInitialization.RandomUniformInitializer(-1, 1, 42));
        NeuralNetwork nn = builder.build();

        try {
            nn.forward(new double[] { 1.0 }); // Size 1, expected 2
            throw new RuntimeException("Expected IllegalArgumentException for wrong input size");
        } catch (IllegalArgumentException e) {
            if (!e.getMessage().contains("expected layer input size 2")) {
                throw new RuntimeException("Wrong message: " + e.getMessage());
            }
        }
    }

    private static void testPreprocessing_StandardScaler() {
        System.out.println("Running testPreprocessing_StandardScaler...");
        StandardScaler scaler = new StandardScaler();
        double[][] data = {
                { 1.0, 2.0 },
                { 2.0, 4.0 },
                { 3.0, 6.0 }
        };

        scaler.fit(data);
        scaler.transform(data);

        // Assert mean is approx 0 and std approx 1
        if (Math.abs(data[1][0]) > 1e-6)
            throw new RuntimeException("Mean check failed");
        if (Math.abs(data[1][1]) > 1e-6)
            throw new RuntimeException("Mean check failed");

        // Point 1: (1-2)/0.816 = -1.22
        if (data[0][0] >= 0)
            throw new RuntimeException("Std check failed");
    }

    private static void testForwardPass() {
        System.out.println("Running testForwardPass...");
        NeuralNetworkBuilder builder = new NeuralNetworkBuilder().inputSize(2);
        builder.addLayer(2, new Sigmoid(),
                new RandomUniformInitializer(-0.5, 0.5, 123));

        // Output layer
        builder.addLayer(1, new Sigmoid(),
                new RandomUniformInitializer(-0.5, 0.5, 123));

        NeuralNetwork nn = builder.build();
        double[] out = nn.forward(new double[] { 0.5, 0.5 });

        if (out == null || out.length != 1)
            throw new RuntimeException("Output size validation failed");
        if (out[0] < 0 || out[0] > 1)
            throw new RuntimeException("Sigmoid output range failed");
    }
}
