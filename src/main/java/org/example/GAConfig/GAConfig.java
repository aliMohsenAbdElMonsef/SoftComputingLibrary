package org.example.GAConfig;

import org.example.Algorithms.GA.Factories.Chromosomes.ChromosomeFactory;
import org.example.Algorithms.GA.chromosomes.Range;
import org.example.Algorithms.GA.crossovers.ICrossOver;
import org.example.Algorithms.GA.replacements.IReplacementStrategy;
import org.example.Algorithms.GA.selections.ISelection;
import org.example.case_studies.GA.functions.Functions;

public class GAConfig {
    public int populationSize;
    public double crossoverRate;
    public double mutationRate;
    public int generations;
    public Range[] ranges;
    public Functions fitnessFunction;
    public ISelection selection;
    public ICrossOver crossover;
    public int selectionSize;
    public IReplacementStrategy replacementStrategy;
    public ChromosomeFactory chromosomeFactory;
    public int numVariables;

    // builder setters can be added here...
}