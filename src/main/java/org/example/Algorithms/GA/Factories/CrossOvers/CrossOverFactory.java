package org.example.Algorithms.GA.Factories.CrossOvers;

import org.example.Algorithms.GA.crossovers.ICrossOver;

public interface CrossOverFactory {
    ICrossOver createCrossOver(Object ... params);
}
