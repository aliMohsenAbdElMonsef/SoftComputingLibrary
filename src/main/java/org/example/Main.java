package org.example;

import org.example.Algorithms.GA.chromosomes.Range;
import org.example.case_studies.GA.GeneticAlgorithm;
import org.example.case_studies.GA.functions.Functions;
import org.example.case_studies.GA.functions.Function2;
import org.example.Algorithms.GA.selection.methods.*;

public class Main {
    public static void main(String[] args) {
        Range[] ranges = {new Range(-5, 5), new Range(-5, 5), new Range(-5, 5), new Range(0, 5)};
        Functions fitnessFunction = new Function2();
        GeneticAlgorithm ga_engine = new GeneticAlgorithm();
        ISelection tournamentSelection = new TournamentSelection(5);
        ISelection rouletteWheelSelection = new RouletteWheelSelection();
        ga_engine.setPopulationSize(50);
        ga_engine.setFitnessFunction(fitnessFunction);
        ga_engine.setCrossoverRate(0.07);
        ga_engine.setMutationRate(0.02);
        ga_engine.setGenerations(100);
        ga_engine.setSelection(tournamentSelection);
        ga_engine.setChromosomeConfig(4, 10, ranges);
        ga_engine.run();
    }
}