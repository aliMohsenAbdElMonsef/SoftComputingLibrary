package org.example.Algorithms.GA.Factories.CrossOvers;

import org.example.Algorithms.GA.crossovers.ICrossOver;
import org.example.Algorithms.GA.crossovers.UniformCrossOver;

public class UniformCrossOverFactory implements CrossOverFactory {
        @Override
        public ICrossOver createCrossOver(Object... params) {
            return new UniformCrossOver();
        }
    }

