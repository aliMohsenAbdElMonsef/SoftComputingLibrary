package org.example.Algorithms.GA.crossovers;

import org.example.Algorithms.GA.chromosomes.Chromosome;
import java.util.Random;


public class SingleCrossOver implements ICrossOver {
    Random rand = new Random();

    @Override
    public Chromosome[] apply(Chromosome parent1, Chromosome parent2) {
        int point = rand.nextInt(parent1.getGenesNum() - 1) + 1;
       return parent1.crossover(parent2, point);
    }
}
