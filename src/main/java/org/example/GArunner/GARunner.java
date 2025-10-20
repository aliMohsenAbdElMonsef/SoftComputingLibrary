package org.example.GArunner;

import org.example.GAConfig.GAConfig;
import org.example.case_studies.GA.GeneticAlgorithm;

public class GARunner {
    public GeneticAlgorithm run(GAConfig config) {
        GeneticAlgorithm ga = new GeneticAlgorithm();
        ga.setPopulationSize(config.populationSize);
        ga.setFitnessFunction(config.fitnessFunction);
        ga.setCrossoverRate(config.crossoverRate);
        ga.setMutationRate(config.mutationRate);
        ga.setGenerations(config.generations);
        ga.setSelection(config.selection);
        ga.setChromosomeConfig(config.numVariables, config.ranges);
        ga.setChromosomeFactory(config.chromosomeFactory);
        ga.setCrossover(config.crossover);
        ga.setReplacementStrategy(config.replacementStrategy, config.selectionSize);
        ga.run();
        return ga;
    }
}
