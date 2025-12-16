package org.example.Algorithms.NN.InputProviders;

import org.example.Algorithms.NN.Config.NNConfig;

import java.util.Scanner;

public class CLIInputProvider implements InputProvider{
    private final Scanner sc = new Scanner(System.in);
    private String datasetPath;

    @Override
    public NNConfig readConfig() {
        NNConfig c = new NNConfig();

        System.out.print("Input size: ");
        c.inputSize = sc.nextInt();

        System.out.print("Hidden layers count: ");
        int h = sc.nextInt();
        for (int i = 0; i < h; i++) {
            System.out.print("Neurons in hidden layer " + (i + 1) + ": ");
            c.hiddenLayers.add(sc.nextInt());
        }

        System.out.print("Output size: ");
        c.outputSize = sc.nextInt();

        System.out.print("Hidden activation: ");
        c.activationHidden = sc.next();

        System.out.print("Output activation: ");
        c.activationOutput = sc.next();

        System.out.print("Initializer: ");
        c.initializer = sc.next();

        System.out.print("Loss: ");
        c.loss = sc.next();

        System.out.print("Learning rate: ");
        c.learningRate = sc.nextDouble();

        System.out.print("Epochs: ");
        c.epochs = sc.nextInt();

        System.out.print("Batch size: ");
        c.batchSize = sc.nextInt();

        System.out.print("CSV path: ");
        datasetPath = sc.next();

        return c;
    }

    @Override
    public String getDatasetPath() {
        return datasetPath;
    }
}
