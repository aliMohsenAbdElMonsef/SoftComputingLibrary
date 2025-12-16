package org.example.Algorithms.NN.Core;

import java.util.ArrayList;
import java.util.List;

public class NeuralNetwork {
    private final List<Layer> layers = new ArrayList<>();

    public void addLayer(Layer layer) {
        layers.add(layer);
    }

    public List<Layer> getLayers() {
        return layers;
    }

    public double[] forward(double[] input) {
        validateInput(input);
        double[] out = input;
        for (Layer layer : layers) {
            out = layer.forward(out);
        }
        return out;
    }

    private void validateInput(double[] input) {
        if (input == null || input.length == 0) {
            throw new IllegalArgumentException("Input cannot be null or empty");
        }
        if (layers.isEmpty()) {
            throw new IllegalStateException("Neural Network has no layers");
        }
        if (input.length != layers.get(0).inputSize) {
            throw new IllegalArgumentException("Input size " + input.length +
                    " does not match expected layer input size " + layers.get(0).inputSize);
        }
    }
}
