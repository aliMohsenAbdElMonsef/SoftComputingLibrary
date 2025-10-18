package org.example.Algorithms.GA.crossovers;

import org.example.Algorithms.GA.chromosomes.Chromosome;

public interface ICrossOver<G> {
    Chromosome<G>[] apply(Chromosome<G> parent1, Chromosome<G> parent2);
}
