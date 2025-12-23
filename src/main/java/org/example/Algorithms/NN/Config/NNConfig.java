package org.example.Algorithms.NN.Config;

import org.example.Algorithms.NN.ActivationFunctions.*;
import org.example.Algorithms.NN.LossFunctions.BinaryCrossEntropy;
import org.example.Algorithms.NN.LossFunctions.LossFunction;
import org.example.Algorithms.NN.LossFunctions.MSE;
import org.example.Algorithms.NN.WeightInitialization.*;

import java.util.ArrayList;
import java.util.List;

public class NNConfig {
    private int inputSize;
    private List<Integer> hiddenLayers = new ArrayList<>();
    private int outputSize;

    private String activationHidden;
    private String activationOutput;

    private String initializer;
    private String loss;

    private double learningRate;
    private int epochs;
    private int batchSize;
    private String optimizer = "sgd";

    private long seed = 42;

    // Getters and Setters

    public int getInputSize() {
        return inputSize;
    }

    public void setInputSize(int inputSize) {
        this.inputSize = inputSize;
    }

    public List<Integer> getHiddenLayers() {
        return hiddenLayers;
    }

    public void setHiddenLayers(List<Integer> hiddenLayers) {
        this.hiddenLayers = hiddenLayers;
    }

    public int getOutputSize() {
        return outputSize;
    }

    public void setOutputSize(int outputSize) {
        this.outputSize = outputSize;
    }

    public String getActivationHidden() {
        return activationHidden;
    }

    public void setActivationHidden(String activationHidden) {
        this.activationHidden = activationHidden;
    }

    public String getActivationOutput() {
        return activationOutput;
    }

    public void setActivationOutput(String activationOutput) {
        this.activationOutput = activationOutput;
    }

    public String getInitializer() {
        return initializer;
    }

    public void setInitializer(String initializer) {
        this.initializer = initializer;
    }

    public String getLoss() {
        return loss;
    }

    public void setLoss(String loss) {
        this.loss = loss;
    }

    public double getLearningRate() {
        return learningRate;
    }

    public void setLearningRate(double learningRate) {
        this.learningRate = learningRate;
    }

    public int getEpochs() {
        return epochs;
    }

    public void setEpochs(int epochs) {
        this.epochs = epochs;
    }

    public int getBatchSize() {
        return batchSize;
    }

    public void setBatchSize(int batchSize) {
        this.batchSize = batchSize;
    }

    public String getOptimizer() {
        return optimizer;
    }

    public void setOptimizer(String optimizer) {
        this.optimizer = optimizer;
    }

    public long getSeed() {
        return seed;
    }

    public void setSeed(long seed) {
        this.seed = seed;
    }

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
