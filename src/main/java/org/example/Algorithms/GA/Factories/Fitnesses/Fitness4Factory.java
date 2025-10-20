package org.example.Algorithms.GA.Factories.Fitnesses;

import org.example.case_studies.GA.functions.Function4;
import org.example.case_studies.GA.functions.Functions;

public class Fitness4Factory implements FitnessFactory{
    @Override
    public Functions create() {
        return new Function4();
    }
    @Override
    public String getName() {
        return "Function 4";
    }
}
