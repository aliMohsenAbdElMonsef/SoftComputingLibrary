package org.example.Algorithms.NN.InputProviders;

import org.example.Algorithms.NN.Config.NNConfig;

public interface InputProvider {
    NNConfig readConfig() throws Exception;

    String getDatasetPath();

    default void validate(NNConfig config) {
        if (config.inputSize <= 0)
            throw new IllegalArgumentException("Input size must be > 0");
        if (config.outputSize <= 0)
            throw new IllegalArgumentException("Output size must be > 0");
        if (config.learningRate <= 0)
            throw new IllegalArgumentException("Learning rate must be > 0");
        if (config.epochs <= 0)
            throw new IllegalArgumentException("Epochs must be > 0");
        if (config.batchSize <= 0)
            throw new IllegalArgumentException("Batch size must be > 0");
        if (config.hiddenLayers != null) {
            for (int h : config.hiddenLayers) {
                if (h <= 0)
                    throw new IllegalArgumentException("Hidden layer neurons must be > 0");
            }
        }
        if (getDatasetPath() == null || getDatasetPath().trim().isEmpty()) {
            throw new IllegalArgumentException("Dataset path cannot be empty");
        }
    }
}
