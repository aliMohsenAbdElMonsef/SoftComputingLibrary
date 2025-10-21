package org.example.Algorithms.GA.BusinessLogic.Factories.Interfaces;

import org.example.Algorithms.GA.BusinessLogic.Contracts.Chromosomes.Chromosome;
import org.example.Algorithms.GA.BusinessLogic.Implementation.Chromosomes.Range;

public interface ChromosomeFactory<G> {
    Chromosome<G> create(int ChromosomeLength, Range[] ranges);
    String getChromosomeType();
}
