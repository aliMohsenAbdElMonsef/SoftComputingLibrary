package org.example.Algorithms.NN.Training;

import org.example.Algorithms.NN.Core.Layer;
import org.example.Algorithms.NN.Core.NeuralNetwork;
import org.example.Algorithms.NN.LossFunctions.LossFunction;

import java.util.List;
import java.util.Random;

public class TrainingEngine {

    private final NeuralNetwork network;
    private final LossFunction loss;
    private final TrainingConfig config;
    private final Random random = new Random(42);

    public TrainingEngine(NeuralNetwork network,
                          LossFunction loss,
                          TrainingConfig config) {

        this.network = network;
        this.loss = loss;
        this.config = config;
    }

    public void train(double[][] x, double[][] y) {

        List<Layer> layers = network.getLayers();
        int n = x.length;
        int batchSize = config.batchSize;

        for (int epoch = 0; epoch < config.epochs; epoch++) {

            shuffle(x, y);

            double totalLoss = 0;

            for (int start = 0; start < n; start += batchSize) {

                int end = Math.min(start + batchSize, n);
                int actualBatchSize = end - start;

                for (Layer layer : layers)
                    layer.zeroGrad();

                for (int i = start; i < end; i++) {

                    double[] pred = network.forward(x[i]);

                    Layer out = layers.get(layers.size() - 1);
                    out.delta = new double[out.outputSize];

                    for (int j = 0; j < out.outputSize; j++) {
                        out.delta[j] =
                                loss.derivative(pred[j], y[i][j]) *
                                        out.activation.derivative(pred[j]);
                        totalLoss += loss.loss(pred[j], y[i][j]);
                    }

                    for (int l = layers.size() - 2; l >= 0; l--) {

                        Layer curr = layers.get(l);
                        Layer next = layers.get(l + 1);

                        curr.delta = new double[curr.outputSize];

                        for (int j = 0; j < curr.outputSize; j++) {
                            double sum = 0;
                            for (int k = 0; k < next.outputSize; k++) {
                                sum += next.weights[k][j + 1] * next.delta[k];
                            }
                            curr.delta[j] =
                                    sum * curr.activation.derivative(curr.output[j]);
                        }
                    }

                    for (Layer layer : layers)
                        layer.accumulateGrad();
                }

                for (Layer layer : layers) {
                    for (int i = 0; i < layer.outputSize; i++) {
                        for (int j = 0; j < layer.inputSize + 1; j++) {
                            layer.weights[i][j] -=
                                    config.learningRate *
                                            (layer.grad[i][j] / actualBatchSize);
                        }
                    }
                }
            }

            totalLoss /= n;
            System.out.println("Epoch " + epoch + " | Loss: " + totalLoss);
        }
    }

    private void shuffle(double[][] x, double[][] y) {

        for (int i = x.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);

            double[] tmpX = x[i];
            x[i] = x[j];
            x[j] = tmpX;

            double[] tmpY = y[i];
            y[i] = y[j];
            y[j] = tmpY;
        }
    }
}
