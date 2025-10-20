package org.example.Algorithms.GA.replacements;

import org.example.Algorithms.GA.chromosomes.Chromosome;

import java.util.List;

public interface IReplacementStrategy {
    List<Chromosome> selectForReproduction(List<Chromosome> population, int k);
    void Replace(List<Chromosome> population, List<Chromosome> selected, List<Chromosome> offspring);
}
