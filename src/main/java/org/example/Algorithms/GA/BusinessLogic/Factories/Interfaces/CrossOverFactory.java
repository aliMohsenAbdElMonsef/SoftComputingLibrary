package org.example.Algorithms.GA.BusinessLogic.Factories.Interfaces;

import org.example.Algorithms.GA.BusinessLogic.Contracts.CrossOvers.ICrossOver;

public interface CrossOverFactory {
    ICrossOver createCrossOver(Object ... params);
}
