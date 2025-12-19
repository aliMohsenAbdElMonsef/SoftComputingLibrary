package org.example.Algorithms.NN.DataHandling.Normalization;

import org.example.Algorithms.NN.DataHandling.Dataset;

import java.util.List;

public class PreprocessingPipeline {
    private final List<Processor> steps;

    public PreprocessingPipeline(List<Processor> steps) {
        this.steps = steps;
    }

    public void fit(Dataset dataset) {
        for (Processor p : steps) {
            p.fit(dataset.xTrain);
            p.transform(dataset.xTrain);
            p.transform(dataset.xTest);
        }
    }
}
