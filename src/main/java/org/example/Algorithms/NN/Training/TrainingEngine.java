package org.example.Algorithms.NN.Training;

import org.example.Algorithms.NN.Core.Layer;
import org.example.Algorithms.NN.Core.NeuralNetwork;
import org.example.Algorithms.NN.LossFunctions.LossFunction;

import java.util.List;

public class TrainingEngine {
    private final NeuralNetwork network;
    private final LossFunction loss;
    private final TrainingConfig config;

    public TrainingEngine(NeuralNetwork network,
                   LossFunction loss,
                   TrainingConfig config) {

        this.network = network;
        this.loss = loss;
        this.config = config;
    }

    public void train(double[][] x, double[][] y) {
        List<Layer> layers = network.getLayers();

        for (int epoch = 0; epoch < config.epochs; epoch++) {
            double totalLoss = 0;

            for (int i = 0; i < x.length; i++) {
                double[] prediction = network.forward(x[i]);

                Layer out = layers.get(layers.size() - 1);
                out.delta = new double[out.outputSize];

                for (int j = 0; j < out.outputSize; j++) {
                    out.delta[j] =
                            loss.derivative(prediction[j], y[i][j]) *
                                    out.activation.derivative(prediction[j]);
                    totalLoss += loss.loss(prediction[j], y[i][j]);
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

                for (Layer layer : layers) {
                    for (int r = 0; r < layer.outputSize; r++) {
                        for (int c = 0; c < layer.input.length; c++) {
                            layer.weights[r][c] -=
                                    config.learningRate *
                                            layer.delta[r] *
                                            layer.input[c];
                        }
                    }
                }
            }

            totalLoss /= x.length;
            System.out.println("Epoch " + epoch + " | Loss: " + totalLoss);
        }
    }
}
