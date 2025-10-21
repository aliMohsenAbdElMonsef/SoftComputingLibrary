package org.example.Algorithms.GA.BusinessLogic.Contracts.CrossOvers;

import org.example.Algorithms.GA.BusinessLogic.Contracts.Chromosomes.Chromosome;

public interface ICrossOver<G> {
    Chromosome<G>[] apply(Chromosome<G> parent1, Chromosome<G> parent2);
}
