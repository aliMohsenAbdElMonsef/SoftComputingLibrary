package org.example.Algorithms.NN;

import org.example.Algorithms.NN.Core.NeuralNetwork;
import org.example.Algorithms.NN.Core.NeuralNetworkBuilder;
import org.example.Algorithms.NN.DataHandling.Normalization.StandardScaler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.example.Algorithms.NN.ActivationFunctions.Relu;

public class NNLibraryTest {

    @Test
    public void testInputValidation_NullInput() {
        NeuralNetwork nn = new NeuralNetwork();
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            nn.forward(null);
        });
        Assertions.assertEquals("Input cannot be null or empty", exception.getMessage());
    }

    @Test
    public void testInputValidation_EmptyInput() {
        NeuralNetwork nn = new NeuralNetwork();
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            nn.forward(new double[] {});
        });
        Assertions.assertEquals("Input cannot be null or empty", exception.getMessage());
    }

    @Test
    public void testInputValidation_EmptyLayers() {
        NeuralNetwork nn = new NeuralNetwork();
        Exception exception = Assertions.assertThrows(IllegalStateException.class, () -> {
            nn.forward(new double[] { 1.0 });
        });
        Assertions.assertEquals("Neural Network has no layers", exception.getMessage());
    }

    @Test
    public void testInputValidation_WrongSize() {
        NeuralNetworkBuilder builder = new NeuralNetworkBuilder().inputSize(2);
        builder.addLayer(2, new org.example.Algorithms.NN.ActivationFunctions.ReLU(),
                new org.example.Algorithms.NN.WeightInitialization.RandomUniformInitializer(-1, 1, 42));
        NeuralNetwork nn = builder.build();

        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            nn.forward(new double[] { 1.0 }); // Size 1, expected 2
        });
        Assertions.assertTrue(exception.getMessage().contains("expected layer input size 2"));
    }

    @Test
    public void testPreprocessing_StandardScaler() {
        StandardScaler scaler = new StandardScaler();
        double[][] data = {
                { 1.0, 2.0 },
                { 2.0, 4.0 },
                { 3.0, 6.0 }
        };
        // Mean: col0=2.0, col1=4.0
        // Std: col0=sqrt((1+0+1)/3)=sqrt(2/3)=0.816, col1=sqrt((4+0+4)/3)=1.632

        scaler.fit(data);
        scaler.transform(data);

        // Assert mean is approx 0 and std approx 1
        // Point 2 (middle) was mean, so it should be exactly 0
        Assertions.assertEquals(0.0, data[1][0], 1e-6, "Mean centered 0");
        Assertions.assertEquals(0.0, data[1][1], 1e-6, "Mean centered 1");

        // Point 1: (1-2)/0.816 = -1.22
        Assertions.assertTrue(data[0][0] < 0);
    }

    @Test
    public void testForwardPass() {
        // Simple XOR-like structure or just pass-through
        NeuralNetworkBuilder builder = new NeuralNetworkBuilder().inputSize(2);
        builder.addLayer(2, new org.example.Algorithms.NN.ActivationFunctions.Sigmoid(),
                new org.example.Algorithms.NN.WeightInitialization.RandomUniformInitializer(-0.5, 0.5, 123));

        // Output layer
        builder.addLayer(1, new org.example.Algorithms.NN.ActivationFunctions.Sigmoid(),
                new org.example.Algorithms.NN.WeightInitialization.RandomUniformInitializer(-0.5, 0.5, 123));

        NeuralNetwork nn = builder.build();
        double[] out = nn.forward(new double[] { 0.5, 0.5 });

        Assertions.assertNotNull(out);
        Assertions.assertEquals(1, out.length);
        Assertions.assertTrue(out[0] >= 0 && out[0] <= 1);
    }
}
