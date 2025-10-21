package org.example.Algorithms.GA.BusinessLogic.Factories.Interfaces;

import org.example.Algorithms.GA.BusinessLogic.Contracts.Functions.Functions;

public interface FitnessFactory {
    Functions create();
    String getName();
}
