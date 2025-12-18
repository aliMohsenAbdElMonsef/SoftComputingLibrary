package org.example.Algorithms.NN.InputProviders;

import org.example.Algorithms.NN.Config.NNConfig;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class FileInputProvider implements InputProvider {
    private final String path;
    private String datasetPath;

    public FileInputProvider(String path) {
        this.path = path;
    }

    @Override
    public NNConfig readConfig() throws Exception {
        NNConfig c = new NNConfig();

        BufferedReader br = new BufferedReader(new FileReader(path));
        String line;

        while ((line = br.readLine()) != null) {
            String[] parts = line.split("=");
            String key = parts[0].trim();
            String value = parts[1].trim();

            switch (key) {
                case "inputSize" -> c.setInputSize(Integer.parseInt(value));
                case "hiddenLayers" -> {
                    for (String s : value.split(",")) {
                        c.getHiddenLayers().add(Integer.parseInt(s));
                    }
                }
                case "outputSize" -> c.setOutputSize(Integer.parseInt(value));
                case "activationHidden" -> c.setActivationHidden(value);
                case "activationOutput" -> c.setActivationOutput(value);
                case "initializer" -> c.setInitializer(value);
                case "loss" -> c.setLoss(value);
                case "learningRate" -> c.setLearningRate(Double.parseDouble(value));
                case "epochs" -> c.setEpochs(Integer.parseInt(value));
                case "batchSize" -> c.setBatchSize(Integer.parseInt(value));
                case "optimizer" -> c.setOptimizer(value);
                case "dataset" -> datasetPath = value;
            }
        }
        br.close();

        if (datasetPath != null && !datasetPath.isEmpty()) {
            java.io.File f = new java.io.File(datasetPath);
            if (!f.exists()) {
                throw new FileNotFoundException("Dataset file not found: " + datasetPath);
            }
        }

        validate(c);
        return c;
    }

    @Override
    public String getDatasetPath() {
        return datasetPath;
    }
}
