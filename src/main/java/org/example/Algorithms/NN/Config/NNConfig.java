package org.example.Algorithms.NN.Config;

import org.example.Algorithms.NN.ActivationFunctions.*;
import org.example.Algorithms.NN.LossFunctions.BinaryCrossEntropy;
import org.example.Algorithms.NN.LossFunctions.LossFunction;
import org.example.Algorithms.NN.LossFunctions.MSE;
import org.example.Algorithms.NN.WeightInitialization.He;
import org.example.Algorithms.NN.WeightInitialization.RandomUniformInitializer;
import org.example.Algorithms.NN.WeightInitialization.WeightInitializer;
import org.example.Algorithms.NN.WeightInitialization.Xavier;

import java.util.ArrayList;
import java.util.List;

public class NNConfig {
    public int inputSize;
    public List<Integer> hiddenLayers = new ArrayList<>();
    public int outputSize;

    public String activationHidden = "relu";
    public String activationOutput = "sigmoid";

    public String initializer = "xavier";
    public String loss = "mse";

    public double learningRate = 0.01;
    public int epochs = 100;
    public int batchSize = 1;

    public long seed = 42;

    public ActivationFunction hiddenActivation() {
        return switch (activationHidden.toLowerCase()) {
            case "sigmoid" -> new Sigmoid();
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
            case "random" -> new RandomUniformInitializer(-0.5, 0.5, seed);
            case "he" -> new He(seed);
            case "xavier" -> new Xavier(seed);
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
