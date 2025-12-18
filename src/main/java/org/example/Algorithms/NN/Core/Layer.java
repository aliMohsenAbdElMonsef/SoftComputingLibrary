package org.example.Algorithms.NN.Core;

import org.example.Algorithms.NN.ActivationFunctions.ActivationFunction;
import org.example.Algorithms.NN.WeightInitialization.WeightInitializer;

public class Layer {
    private final int inputSize;
    private final int outputSize;

    private final ActivationFunction activation;
    private final double[][] weights;

    private double[] input;
    private double[] net;
    private double[] output;
    private double[] delta;
    private double[][] grad;

    public Layer(int inputSize,
            int outputSize,
            ActivationFunction activation,
            WeightInitializer initializer) {

        this.inputSize = inputSize;
        this.outputSize = outputSize;
        this.activation = activation;
        this.weights = initializer.initialize(outputSize, inputSize + 1);
        this.grad = new double[outputSize][inputSize + 1];
    }

    public double[] forward(double[] input) {
        this.input = new double[inputSize + 1];
        this.input[0] = 1.0;
        System.arraycopy(input, 0, this.input, 1, inputSize);

        net = new double[outputSize];
        output = new double[outputSize];

        for (int i = 0; i < outputSize; i++) {
            net[i] = 0;
            for (int j = 0; j < this.input.length; j++) {
                net[i] += weights[i][j] * this.input[j];
            }
            output[i] = activation.activate(net[i]);
        }
        return output;
    }

    public void zeroGrad() {
        for (int i = 0; i < outputSize; i++)
            for (int j = 0; j < inputSize + 1; j++)
                grad[i][j] = 0.0;
    }

    public void accumulateGrad() {
        for (int i = 0; i < outputSize; i++) {
            for (int j = 0; j < input.length; j++) {
                grad[i][j] += delta[i] * input[j];
            }
        }
    }

    public int getInputSize() {
        return inputSize;
    }

    public int getOutputSize() {
        return outputSize;
    }

    public ActivationFunction getActivation() {
        return activation;
    }

    public double[][] getWeights() {
        return weights;
    }

    public double[] getInput() {
        return input;
    }

    public double[] getNet() {
        return net;
    }

    public double[] getOutput() {
        return output;
    }

    public double[] getDelta() {
        return delta;
    }

    public void setDelta(double[] delta) {
        this.delta = delta;
    }

    public double[][] getGrad() {
        return grad;
    }
}
