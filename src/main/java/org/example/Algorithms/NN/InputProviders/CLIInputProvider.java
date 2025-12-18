package org.example.Algorithms.NN.InputProviders;

import org.example.Algorithms.NN.Config.NNConfig;

import java.util.Scanner;

public class CLIInputProvider implements InputProvider {
    private final Scanner sc = new Scanner(System.in);
    private String datasetPath;

    @Override
    public NNConfig readConfig() {
        NNConfig c = new NNConfig();

        System.out.print("Input size: ");
        c.setInputSize(sc.nextInt());

        System.out.print("Hidden layers count: ");
        int h = sc.nextInt();
        for (int i = 0; i < h; i++) {
            System.out.print("Neurons in hidden layer " + (i + 1) + ": ");
            c.getHiddenLayers().add(sc.nextInt());
        }

        System.out.print("Output size: ");
        c.setOutputSize(sc.nextInt());

        System.out.print("Hidden activation: ");
        c.setActivationHidden(sc.next());

        System.out.print("Output activation: ");
        c.setActivationOutput(sc.next());

        System.out.print("Initializer: ");
        c.setInitializer(sc.next());

        System.out.print("Loss: ");
        c.setLoss(sc.next());

        System.out.print("Learning rate: ");
        c.setLearningRate(sc.nextDouble());

        System.out.print("Epochs: ");
        c.setEpochs(sc.nextInt());

        System.out.print("Batch size: ");
        c.setBatchSize(sc.nextInt());

        System.out.print("Optimizer (sgd/adam) [default: sgd]: ");
        String optimizerInput = sc.next();
        c.setOptimizer(optimizerInput.isEmpty() ? "sgd" : optimizerInput);

        System.out.print("CSV path: ");
        datasetPath = sc.next();

        validate(c);
        return c;
    }

    @Override
    public String getDatasetPath() {
        return datasetPath;
    }
}
