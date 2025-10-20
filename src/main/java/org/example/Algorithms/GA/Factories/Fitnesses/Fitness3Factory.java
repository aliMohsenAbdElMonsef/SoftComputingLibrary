package org.example.Algorithms.GA.Factories.Fitnesses;

import org.example.case_studies.GA.functions.Function3;
import org.example.case_studies.GA.functions.Functions;

public class Fitness3Factory implements FitnessFactory{
    @Override
    public Functions create() {
        return new Function3();
    }
    @Override
    public String getName() {
        return "Function 3";
    }
}
