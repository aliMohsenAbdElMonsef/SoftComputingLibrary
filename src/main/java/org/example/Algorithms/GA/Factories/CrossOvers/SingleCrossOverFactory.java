package org.example.Algorithms.GA.Factories.CrossOvers;

import org.example.Algorithms.GA.crossovers.ICrossOver;
import org.example.Algorithms.GA.crossovers.SingleCrossOver;

public class SingleCrossOverFactory implements CrossOverFactory {
    @Override
    public ICrossOver createCrossOver(Object... params) {
        return new SingleCrossOver();
    }
}
