package org.example.Algorithms.GA.crossovers;

import org.example.Algorithms.GA.chromosomes.Chromosome;
import java.util.Random;


public class SingleCrossOver<G> implements ICrossOver<G> {
    Random rand = new Random();

    @Override
    public Chromosome<G>[] apply(Chromosome<G> parent1, Chromosome<G> parent2) {
        int geneNums = parent1.getGenesNum();
        int point = rand.nextInt(geneNums - 1) + 1;
        Chromosome<G> child1 = parent1.copy();
        Chromosome<G> child2 = parent2.copy();
        for (int i = point; i < geneNums; i++)
        {
            child1.swapGene(child2, i);
        }
        return new Chromosome[]{child1, child2};
    }
}
