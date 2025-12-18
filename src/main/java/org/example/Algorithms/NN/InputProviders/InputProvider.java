package org.example.Algorithms.NN.InputProviders;

import org.example.Algorithms.NN.Config.NNConfig;

public interface InputProvider {
    NNConfig readConfig() throws Exception;

    String getDatasetPath();

    default void validate(NNConfig config) {
        if (config.getInputSize() <= 0)
            throw new IllegalArgumentException("Input size must be > 0");
        if (config.getOutputSize() <= 0)
            throw new IllegalArgumentException("Output size must be > 0");
        if (config.getLearningRate() <= 0)
            throw new IllegalArgumentException("Learning rate must be > 0");
        if (config.getEpochs() <= 0)
            throw new IllegalArgumentException("Epochs must be > 0");
        if (config.getBatchSize() <= 0)
            throw new IllegalArgumentException("Batch size must be > 0");
        if (config.getHiddenLayers() != null) {
            for (int h : config.getHiddenLayers()) {
                if (h <= 0)
                    throw new IllegalArgumentException("Hidden layer neurons must be > 0");
            }
        }
        if (getDatasetPath() == null || getDatasetPath().trim().isEmpty()) {
            throw new IllegalArgumentException("Dataset path cannot be empty");
        }
    }
}
