package org.example.Algorithms.GA.crossovers;

import org.example.Algorithms.GA.chromosomes.Chromosome;

public interface ICrossOver {
    Chromosome[] apply(Chromosome parent1, Chromosome parent2);
}
