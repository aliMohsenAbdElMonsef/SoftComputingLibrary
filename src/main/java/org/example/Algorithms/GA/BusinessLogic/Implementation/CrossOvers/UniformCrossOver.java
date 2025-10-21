package org.example.Algorithms.GA.BusinessLogic.Implementation.CrossOvers;

import org.example.Algorithms.GA.BusinessLogic.Contracts.Chromosomes.Chromosome;
import org.example.Algorithms.GA.BusinessLogic.Contracts.CrossOvers.ICrossOver;

import java.util.Random;

public class UniformCrossOver implements ICrossOver {
    @Override
    public Chromosome[] apply(Chromosome parent1, Chromosome parent2) {
        Random rand = new Random();
        Chromosome child1 = parent1.copy();
        Chromosome child2 = parent2.copy();
        int length = parent1.getGenesNum();

        for (int i = 0; i < length; i++) {
            if (rand.nextBoolean()) {
                child1.swapGene(child2, i);
            }
        }


        return new Chromosome[]{child1, child2};
    }


}
