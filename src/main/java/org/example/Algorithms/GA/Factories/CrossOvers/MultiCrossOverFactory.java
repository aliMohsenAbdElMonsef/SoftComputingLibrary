package org.example.Algorithms.GA.Factories.CrossOvers;

import org.example.Algorithms.GA.crossovers.ICrossOver;
import org.example.Algorithms.GA.crossovers.MultiCrossOver;

public class MultiCrossOverFactory implements CrossOverFactory {
    @Override
    public ICrossOver createCrossOver(Object... params) {
        if (params.length < 1 || !(params[0] instanceof Integer)) {
            throw new IllegalArgumentException("MultiCrossOver requires numPoints as parameter");
        }
        int points = (Integer) params[0];
        return new MultiCrossOver(points);
    }
}