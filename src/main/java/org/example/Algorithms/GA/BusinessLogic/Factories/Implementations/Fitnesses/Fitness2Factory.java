package org.example.Algorithms.GA.BusinessLogic.Factories.Implementations.Fitnesses;

import org.example.Algorithms.GA.BusinessLogic.Factories.Interfaces.FitnessFactory;
import org.example.Algorithms.GA.BusinessLogic.Implementation.Functions.Function2;
import org.example.Algorithms.GA.BusinessLogic.Contracts.Functions.Functions;

public class Fitness2Factory implements FitnessFactory {
    @Override
    public Functions create() {
        return new Function2();
    }
    @Override
    public String getName() {
        return "Function 2";
    }
}
