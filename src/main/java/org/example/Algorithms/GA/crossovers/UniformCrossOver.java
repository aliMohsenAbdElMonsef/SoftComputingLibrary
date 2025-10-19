package org.example.Algorithms.GA.crossovers;

import org.example.Algorithms.GA.chromosomes.Chromosome;

import java.util.Random;

public class UniformCrossOver implements ICrossOver {
    @Override
    public Chromosome[] apply(Chromosome parent1, Chromosome parent2) {
        Random rand = new Random();
        Chromosome child1 = parent1.copy();
        Chromosome child2 = parent2.copy();

        Object genes1 = parent1.getGenes();
        Object genes2 = parent2.getGenes();

        if (genes1 instanceof int[] && genes2 instanceof int[]) {
            int[] g1 = (int[]) genes1;
            int[] g2 = (int[]) genes2;

            for (int i = 0; i < g1.length; i++) {
                if (rand.nextBoolean()) { // randomly swap genes
                    int temp = g1[i];
                    g1[i] = g2[i];
                    g2[i] = temp;
                }
            }
            child1.setGenes(g1);
            child2.setGenes(g2);
        }

        return new Chromosome[]{child1, child2};
    }

}
