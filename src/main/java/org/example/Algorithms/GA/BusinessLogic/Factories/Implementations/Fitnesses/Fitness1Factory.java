package org.example.Algorithms.GA.BusinessLogic.Factories.Implementations.Fitnesses;

import org.example.Algorithms.GA.BusinessLogic.Factories.Interfaces.FitnessFactory;
import org.example.Algorithms.GA.BusinessLogic.Implementation.Functions.Function1;
import org.example.Algorithms.GA.BusinessLogic.Contracts.Functions.Functions;

public class Fitness1Factory implements FitnessFactory {
    @Override
    public Functions create() {
        return new Function1();
    }

    @Override
    public String getName() {
        return "Function 1";
    }
}
