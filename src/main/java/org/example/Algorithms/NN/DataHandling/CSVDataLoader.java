package org.example.Algorithms.NN.DataHandling;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CSVDataLoader {

    public static Dataset loadAndSplit(
            String path,
            double testRatio,
            long seed) throws IOException, CsvException {

        CSVReader reader = new CSVReader(new FileReader(path));
        List<String[]> rows = reader.readAll();
        reader.close();

        if (!rows.isEmpty()) {
            rows.remove(0);
        }

        if (rows.isEmpty()) {
            throw new IllegalArgumentException("Dataset is empty.");
        }

        int n = rows.size();
        int totalCols = rows.get(0).length;
        int m = totalCols - 1;

        Map<Integer, CategoricalEncoder> encoders = new HashMap<>();

        for (int j = 0; j < totalCols; j++) {
            boolean isNumeric = true;
            try {
                String val = rows.get(0)[j];
                Double.parseDouble(val);
            } catch (NumberFormatException e) {
                isNumeric = false;
            }

            if (!isNumeric) {
                List<String> colValues = new ArrayList<>();
                for (String[] row : rows) {
                    colValues.add(row[j]);
                }
                CategoricalEncoder encoder = new CategoricalEncoder();
                encoder.fit(colValues);
                encoders.put(j, encoder);
            }
        }

        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < n; i++)
            indices.add(i);
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
                if (encoders.containsKey(j)) {
                    xTrain[i][j] = encoders.get(j).transform(row[j]);
                } else {
                    xTrain[i][j] = Double.parseDouble(row[j]);
                }
            }
            if (encoders.containsKey(m)) {
                yTrain[i] = encoders.get(m).transform(row[m]);
            } else {
                yTrain[i] = Double.parseDouble(row[m]);
            }
        }

        for (int i = 0; i < testSize; i++) {
            String[] row = rows.get(indices.get(i + trainSize));
            for (int j = 0; j < m; j++) {
                if (encoders.containsKey(j)) {
                    xTest[i][j] = encoders.get(j).transform(row[j]);
                } else {
                    xTest[i][j] = Double.parseDouble(row[j]);
                }
            }
            if (encoders.containsKey(m)) {
                yTest[i] = encoders.get(m).transform(row[m]);
            } else {
                yTest[i] = Double.parseDouble(row[m]);
            }
        }

        Dataset dataset = new Dataset();
        dataset.xTrain = xTrain;
        dataset.xTest = xTest;
        dataset.yTrain = yTrain;
        dataset.yTest = yTest;

        return dataset;
    }
}
