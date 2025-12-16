package org.example.Algorithms.NN.Core;

import org.example.Algorithms.NN.ActivationFunctions.ActivationFunction;
import org.example.Algorithms.NN.WeightInitialization.WeightInitializer;

public class NeuralNetworkBuilder {
    private final NeuralNetwork network = new NeuralNetwork();
    private int lastSize;

    public NeuralNetworkBuilder inputSize(int size) {
        this.lastSize = size;
        return this;
    }

    public NeuralNetworkBuilder addLayer(int neurons,
                                   ActivationFunction activation,
                                   WeightInitializer initializer) {

        network.addLayer(
                new Layer(lastSize, neurons, activation, initializer)
        );
        lastSize = neurons;
        return this;
    }

    public NeuralNetwork build() {
        return network;
    }
}
