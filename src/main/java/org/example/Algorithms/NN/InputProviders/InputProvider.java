package org.example.Algorithms.NN.InputProviders;

import org.example.Algorithms.NN.Config.NNConfig;

public interface InputProvider {
    NNConfig readConfig() throws Exception;
    String getDatasetPath();
}
