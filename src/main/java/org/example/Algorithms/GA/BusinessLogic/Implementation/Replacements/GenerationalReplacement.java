package org.example.Algorithms.GA.BusinessLogic.Implementation.Replacements;

import org.example.Algorithms.GA.BusinessLogic.Contracts.Chromosomes.Chromosome;
import org.example.Algorithms.GA.BusinessLogic.Contracts.Replacements.IReplacementStrategy;

import java.util.ArrayList;
import java.util.List;

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