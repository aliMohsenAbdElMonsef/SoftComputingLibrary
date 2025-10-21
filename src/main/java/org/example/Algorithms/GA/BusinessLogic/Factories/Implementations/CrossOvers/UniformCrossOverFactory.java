package org.example.Algorithms.GA.BusinessLogic.Factories.Implementations.CrossOvers;

import org.example.Algorithms.GA.BusinessLogic.Contracts.CrossOvers.ICrossOver;
import org.example.Algorithms.GA.BusinessLogic.Factories.Interfaces.CrossOverFactory;
import org.example.Algorithms.GA.BusinessLogic.Implementation.CrossOvers.UniformCrossOver;

public class UniformCrossOverFactory implements CrossOverFactory {
        @Override
        public ICrossOver createCrossOver(Object... params) {
            return new UniformCrossOver();
        }
    }

