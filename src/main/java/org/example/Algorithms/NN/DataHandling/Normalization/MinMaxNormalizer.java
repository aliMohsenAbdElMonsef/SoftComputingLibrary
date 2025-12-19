package org.example.Algorithms.NN.DataHandling.Normalization;

public class MinMaxNormalizer implements Processor{
    private double[] min;
    private double[] max;

    @Override
    public void fit(double[][] xTrain) {
        int cols = xTrain[0].length;
        min = new double[cols];
        max = new double[cols];

        for (int j = 0; j < cols; j++) {
            min[j] = Double.POSITIVE_INFINITY;
            max[j] = Double.NEGATIVE_INFINITY;

            for (double[] row : xTrain) {
                min[j] = Math.min(min[j], row[j]);
                max[j] = Math.max(max[j], row[j]);
            }
        }
    }

    @Override
    public void transform(double[][] x) {
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x[0].length; j++) {
                if (max[j] - min[j] == 0) {
                    x[i][j] = 0;
                } else {
                    x[i][j] = (x[i][j] - min[j]) / (max[j] - min[j]);
                }
            }
        }
    }
}
