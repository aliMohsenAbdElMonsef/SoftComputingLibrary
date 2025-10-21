package org.example.Algorithms.GA.BusinessLogic.Factories.Implementations.CrossOvers;

import org.example.Algorithms.GA.BusinessLogic.Contracts.CrossOvers.ICrossOver;
import org.example.Algorithms.GA.BusinessLogic.Factories.Interfaces.CrossOverFactory;
import org.example.Algorithms.GA.BusinessLogic.Implementation.CrossOvers.MultiCrossOver;

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