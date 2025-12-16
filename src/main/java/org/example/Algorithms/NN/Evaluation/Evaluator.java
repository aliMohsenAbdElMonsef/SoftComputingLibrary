package org.example.Algorithms.NN.Evaluation;

import org.example.Algorithms.NN.Core.NeuralNetwork;
import org.example.Algorithms.NN.LossFunctions.LossFunction;

public class Evaluator {
    private final NeuralNetwork network;
    private final LossFunction loss;

    public Evaluator(NeuralNetwork network, LossFunction loss) {
        this.network = network;
        this.loss = loss;
    }

    public double evaluateLoss(double[][] xTest, double[][] yTest) {
        double totalLoss = 0;

        for (int i = 0; i < xTest.length; i++) {
            double[] pred = network.forward(xTest[i]);
            for (int j = 0; j < pred.length; j++) {
                totalLoss += loss.loss(pred[j], yTest[i][j]);
            }
        }
        return totalLoss / xTest.length;
    }

    public double accuracy(double[][] xTest, double[][] yTest) {
        int correct = 0;

        for (int i = 0; i < xTest.length; i++) {
            double[] pred = network.forward(xTest[i]);
            int predicted = pred[0] >= 0.5 ? 1 : 0;
            int actual = (int) yTest[i][0];
            if (predicted == actual) correct++;
        }
        return (double) correct / xTest.length;
    }
}
