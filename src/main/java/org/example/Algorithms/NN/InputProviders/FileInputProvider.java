package org.example.Algorithms.NN.InputProviders;

import org.example.Algorithms.NN.Config.NNConfig;

import java.io.BufferedReader;
import java.io.FileReader;

public class FileInputProvider implements InputProvider{
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
                case "inputSize" -> c.inputSize = Integer.parseInt(value);
                case "hiddenLayers" -> {
                    for (String s : value.split(",")) {
                        c.hiddenLayers.add(Integer.parseInt(s));
                    }
                }
                case "outputSize" -> c.outputSize = Integer.parseInt(value);
                case "activationHidden" -> c.activationHidden = value;
                case "activationOutput" -> c.activationOutput = value;
                case "initializer" -> c.initializer = value;
                case "loss" -> c.loss = value;
                case "learningRate" -> c.learningRate = Double.parseDouble(value);
                case "epochs" -> c.epochs = Integer.parseInt(value);
                case "batchSize" -> c.batchSize = Integer.parseInt(value);
                case "dataset" -> datasetPath = value;
            }
        }
        br.close();
        return c;
    }

    @Override
    public String getDatasetPath() {
        return datasetPath;
    }
}
