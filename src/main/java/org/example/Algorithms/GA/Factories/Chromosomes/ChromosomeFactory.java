package org.example.Algorithms.GA.Factories.Chromosomes;

import org.example.Algorithms.GA.chromosomes.Chromosome;
import org.example.Algorithms.GA.chromosomes.Range;

public interface ChromosomeFactory<G> {
    Chromosome<G> create(int ChromosomeLength, Range[] ranges);
    String getChromosomeType();
}
