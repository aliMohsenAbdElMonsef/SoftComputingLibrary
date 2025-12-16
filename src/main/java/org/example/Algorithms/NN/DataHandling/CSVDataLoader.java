package org.example.Algorithms.NN.DataHandling;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class CSVDataLoader {

    public static Dataset loadAndSplit(
            String path,
            double testRatio,
            long seed
    ) throws IOException, CsvException {

        CSVReader reader = new CSVReader(new FileReader(path));
        List<String[]> rows = reader.readAll();
        reader.close();

        rows.remove(0);

        int n = rows.size();
        int m = rows.get(0).length - 1;

        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < n; i++) indices.add(i);
        Collections.shuffle(indices, new Random(seed));

        int testSize = (int) (n * testRatio);
        int trainSize = n - testSize;

        double[][] xTrain = new double[trainSize][m];
        double[][] xTest = new double[testSize][m];
        double[] yTrain = new double[trainSize];
        double[] yTest = new double[testSize];

        for (int i = 0; i < trainSize; i++) {
            String[] row = rows.get(indices.get(i));
            for (int j = 0; j < m; j++) {
                xTrain[i][j] = Double.parseDouble(row[j]);
            }
            yTrain[i] = Double.parseDouble(row[m]);
        }

        for (int i = 0; i < testSize; i++) {
            String[] row = rows.get(indices.get(i + trainSize));
            for (int j = 0; j < m; j++) {
                xTest[i][j] = Double.parseDouble(row[j]);
            }
            yTest[i] = Double.parseDouble(row[m]);
        }
        Dataset dataset = new Dataset();
        dataset.xTrain = xTrain;
        dataset.xTest = xTest;
        dataset.yTrain = yTrain;
        dataset.yTest = yTest;

        return dataset;
    }
}
