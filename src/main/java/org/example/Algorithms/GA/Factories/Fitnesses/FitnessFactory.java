package org.example.Algorithms.GA.Factories.Fitnesses;

import org.example.case_studies.GA.functions.Functions;

public interface FitnessFactory {
    Functions create();
    String getName();
}
