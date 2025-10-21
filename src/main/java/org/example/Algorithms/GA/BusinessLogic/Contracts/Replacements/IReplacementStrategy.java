package org.example.Algorithms.GA.BusinessLogic.Contracts.Replacements;

import org.example.Algorithms.GA.BusinessLogic.Contracts.Chromosomes.Chromosome;

import java.util.List;

public interface IReplacementStrategy {
    List<Chromosome> selectForReproduction(List<Chromosome> population, int k);
    void Replace(List<Chromosome> population, List<Chromosome> selected, List<Chromosome> offspring);
}
