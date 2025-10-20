package org.example.Algorithms.GA.Factories.Fitnesses;

import org.example.case_studies.GA.functions.Function2;
import org.example.case_studies.GA.functions.Functions;

public class Fitness2Factory implements FitnessFactory{
    @Override
    public Functions create() {
        return new Function2();
    }
    @Override
    public String getName() {
        return "Function 2";
    }
}
