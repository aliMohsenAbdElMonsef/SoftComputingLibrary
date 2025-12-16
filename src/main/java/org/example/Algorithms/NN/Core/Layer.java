package org.example.Algorithms.NN.Core;

import org.example.Algorithms.NN.ActivationFunctions.ActivationFunction;
import org.example.Algorithms.NN.WeightInitialization.WeightInitializer;

public class Layer {
    public final int inputSize;
    public final int outputSize;

    public final ActivationFunction activation;
    public final double[][] weights;

    public double[] input;
    public double[] net;
    public double[] output;
    public double[] delta;

    public Layer(int inputSize,
            int outputSize,
            ActivationFunction activation,
            WeightInitializer initializer) {

        this.inputSize = inputSize;
        this.outputSize = outputSize;
        this.activation = activation;
        this.weights = initializer.initialize(outputSize, inputSize + 1);
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
}
