package org.example.Algorithms.GA.Factories.Fitnesses;

import org.example.case_studies.GA.functions.Function5;
import org.example.case_studies.GA.functions.Functions;

public class Fitness5Factory implements FitnessFactory{
    @Override
    public Functions create() {
        return new Function5();
    }
    @Override
    public String getName() {
        return "Function 5";
    }
}
