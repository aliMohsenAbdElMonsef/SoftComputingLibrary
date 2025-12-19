package org.example.Algorithms.NN.DataHandling.Normalization;


public class StandardScaler implements Processor {
    private double[] mean;
    private double[] std;

    @Override
    public void fit(double[][] xTrain) {
        int rows = xTrain.length;
        int cols = xTrain[0].length;

        mean = new double[cols];
        std = new double[cols];

        for (int j = 0; j < cols; j++) {
            for (int i = 0; i < rows; i++) {
                mean[j] += xTrain[i][j];
            }
            mean[j] /= rows;

            for (int i = 0; i < rows; i++) {
                double d = xTrain[i][j] - mean[j];
                std[j] += d * d;
            }
            std[j] = Math.sqrt(std[j] / rows);
            if (std[j] == 0) std[j] = 1;
        }
    }

    @Override
    public void transform(double[][] x) {
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x[0].length; j++) {
                x[i][j] = (x[i][j] - mean[j]) / std[j];
            }
        }
    }
}
