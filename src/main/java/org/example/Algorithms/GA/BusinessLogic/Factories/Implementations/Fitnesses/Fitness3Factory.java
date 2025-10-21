package org.example.Algorithms.GA.BusinessLogic.Factories.Implementations.Fitnesses;

import org.example.Algorithms.GA.BusinessLogic.Factories.Interfaces.FitnessFactory;
import org.example.Algorithms.GA.BusinessLogic.Implementation.Functions.Function3;
import org.example.Algorithms.GA.BusinessLogic.Contracts.Functions.Functions;

public class Fitness3Factory implements FitnessFactory {
    @Override
    public Functions create() {
        return new Function3();
    }
    @Override
    public String getName() {
        return "Function 3";
    }
}
