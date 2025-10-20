package org.example.Algorithms.GA.replacements;

import org.example.Algorithms.GA.chromosomes.Chromosome;
import org.example.Algorithms.GA.crossovers.ICrossOver;
import org.example.Algorithms.GA.selections.ISelection;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GenerationalReplacement implements IReplacementStrategy {
    @Override
    public List<Chromosome> selectForReproduction(List<Chromosome> population, int k) {
        k = 50;
        return new ArrayList<>(population);
    }

    @Override
    public void Replace(List<Chromosome> population, List<Chromosome> selected,List<Chromosome> offspring) {
        population.clear();
        population.addAll(offspring);
    }

}