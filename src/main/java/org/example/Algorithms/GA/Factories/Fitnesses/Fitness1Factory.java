package org.example.Algorithms.GA.Factories.Fitnesses;

import org.example.case_studies.GA.functions.Function1;
import org.example.case_studies.GA.functions.Functions;

public class Fitness1Factory implements FitnessFactory{
    @Override
    public Functions create() {
        return new Function1();
    }

    @Override
    public String getName() {
        return "Function 1";
    }
}
