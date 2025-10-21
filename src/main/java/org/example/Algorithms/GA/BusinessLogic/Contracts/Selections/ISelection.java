package org.example.Algorithms.GA.BusinessLogic.Contracts.Selections;

import org.example.Algorithms.GA.BusinessLogic.Contracts.Chromosomes.Chromosome;
import java.util.List;

public interface ISelection {
    Chromosome select(List<? extends Chromosome> population);
}