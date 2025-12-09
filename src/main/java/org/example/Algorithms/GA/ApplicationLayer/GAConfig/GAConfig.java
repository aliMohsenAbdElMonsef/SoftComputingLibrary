package org.example.Algorithms.GA.ApplicationLayer.GAConfig;

import org.example.Algorithms.GA.BusinessLogic.Factories.Interfaces.ChromosomeFactory;
import org.example.Algorithms.GA.BusinessLogic.Implementation.Chromosomes.Range;
import org.example.Algorithms.GA.BusinessLogic.Contracts.CrossOvers.ICrossOver;
import org.example.Algorithms.GA.BusinessLogic.Contracts.Replacements.IReplacementStrategy;
import org.example.Algorithms.GA.BusinessLogic.Contracts.Selections.ISelection;
import org.example.Algorithms.GA.BusinessLogic.Contracts.Functions.Functions;

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


}