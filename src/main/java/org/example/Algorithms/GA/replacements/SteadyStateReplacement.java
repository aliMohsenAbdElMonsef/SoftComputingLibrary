package org.example.Algorithms.GA.replacements;

import org.example.Algorithms.GA.chromosomes.Chromosome;

import java.util.*;

public class SteadyStateReplacement implements IReplacementStrategy {

    @Override
    public List<Chromosome> selectForReproduction(List<Chromosome> population, int k) {
        List<Chromosome> selected = new ArrayList<>();
        Random rand = new Random();
        Set<Integer> indices = new HashSet<>();
        HelpingMethods.checkSelectedElements(k);

        while (indices.size() < k) {
            indices.add(rand.nextInt(population.size()));
        }

        for (int idx : indices) {
            selected.add(population.get(idx));
        }
        return selected;
    }

    @Override
    public void Replace(List<Chromosome> population, List<Chromosome> selected,List<Chromosome> offspring) {
        for (int i = 0; i < selected.size(); i++) {
            int idx = population.indexOf(selected.get(i));
            population.set(idx, offspring.get(i));
        }

    }

}
