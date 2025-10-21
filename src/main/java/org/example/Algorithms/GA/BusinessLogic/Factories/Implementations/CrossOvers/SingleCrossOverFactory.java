package org.example.Algorithms.GA.BusinessLogic.Factories.Implementations.CrossOvers;

import org.example.Algorithms.GA.BusinessLogic.Contracts.CrossOvers.ICrossOver;
import org.example.Algorithms.GA.BusinessLogic.Factories.Interfaces.CrossOverFactory;
import org.example.Algorithms.GA.BusinessLogic.Implementation.CrossOvers.SingleCrossOver;

public class SingleCrossOverFactory implements CrossOverFactory {
    @Override
    public ICrossOver createCrossOver(Object... params) {
        return new SingleCrossOver();
    }
}
