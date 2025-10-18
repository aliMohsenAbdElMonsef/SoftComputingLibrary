package org.example.Algorithms.GA.selection.methods;

import org.example.Algorithms.GA.chromosomes.Chromosome;
import java.util.List;

public interface ISelection {
    Chromosome select(List<? extends Chromosome> population);
}