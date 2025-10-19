package org.example.Algorithms.GA.crossovers;

import org.example.Algorithms.GA.chromosomes.Chromosome;

import java.util.Random;

public class MultiCrossOver implements ICrossOver{
    private Random rand = new Random();

    @Override
    public Chromosome[] apply(Chromosome parent1, Chromosome parent2) {
        int genesNum = parent1.getGenesNum();

        // Create two crossover points
        int point1 = rand.nextInt(genesNum - 1) + 1;
        int point2 = rand.nextInt(genesNum - point1) + point1;

        // Perform multi-point crossover
        Chromosome[] offspring1 = parent1.crossover(parent2, point1);
        Chromosome[] offspring2 = offspring1[0].crossover(offspring1[1], point2);

        return offspring2;
    }
}
