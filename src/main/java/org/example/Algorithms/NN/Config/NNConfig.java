package org.example.Algorithms.NN.Config;

import org.example.Algorithms.NN.ActivationFunctions.*;
import org.example.Algorithms.NN.LossFunctions.BinaryCrossEntropy;
import org.example.Algorithms.NN.LossFunctions.LossFunction;
import org.example.Algorithms.NN.LossFunctions.MSE;
import org.example.Algorithms.NN.WeightInitialization.*;

import java.util.ArrayList;
import java.util.List;

public class NNConfig {
    public int inputSize;
    public List<Integer> hiddenLayers = new ArrayList<>();
    public int outputSize;

    public String activationHidden;
    public String activationOutput;

    public String initializer;
    public String loss;

    public double learningRate;
    public int epochs;
    public int batchSize;

    public long seed = 42;

    public ActivationFunction hiddenActivation() {
        return switch (activationHidden.toLowerCase()) {
            case "sigmoid" -> new Sigmoid();
            case "linear" -> new Linear();
            case "tanh" -> new Tanh();
            case "relu" -> new ReLU();
            case "step" -> new BinaryStep();
            default -> throw new IllegalArgumentException("Unknown activation");
        };
    }

    public ActivationFunction outputActivation() {
        return switch (activationOutput.toLowerCase()) {
            case "sigmoid" -> new Sigmoid();
            case "tanh" -> new Tanh();
            case "step" -> new BinaryStep();
            default -> throw new IllegalArgumentException("Unknown output activation");
        };
    }

    public WeightInitializer initializer() {
        return switch (initializer.toLowerCase()) {
            case "random" -> InitializerFactory.randomUniform(seed);
            case "he" -> InitializerFactory.he(seed);
            case "xavier" -> InitializerFactory.xavier(seed);
            default -> throw new IllegalArgumentException("Unknown initializer");
        };
    }

    public LossFunction lossFunction() {
        return switch (loss.toLowerCase()) {
            case "mse" -> new MSE();
            case "crossentropy" -> new BinaryCrossEntropy();
            default -> throw new IllegalArgumentException("Unknown loss");
        };
    }
}
