package org.example.Algorithms.GA.replacements;

import org.example.Algorithms.GA.chromosomes.Chromosome;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ElitistStrategy implements IReplacementStrategy {
    @Override
    public List<Chromosome> selectForReproduction(List<Chromosome> population, int k) {
        population.sort(Comparator.comparingDouble(Chromosome::getFitness));
        List<Chromosome> selected = new ArrayList<>();
        for (int i = 0; i < k; i++) { // pick k best
            selected.add(population.get(i));
        }
        return selected;
    }

    @Override
    public void Replace(List<Chromosome> population, List<Chromosome> selected, List<Chromosome> offspring) {
        population.sort(Comparator.comparingDouble(Chromosome::getFitness));
        for (int i = 0; i < selected.size(); i++) {
            population.set(i, offspring.get(i));
        }

    }

}
