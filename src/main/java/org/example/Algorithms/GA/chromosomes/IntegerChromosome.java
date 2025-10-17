package org.example.Algorithms.GA.chromosomes;

import java.util.Arrays;
import java.util.Random;

public class IntegerChromosome extends Chromosome {

    private int[] genes;
    private Range[] ranges;
    public IntegerChromosome(int geneCount, Range[] _ranges) {

        this.genes = new int[geneCount];
        Random rand = new Random();
        this.ranges = _ranges;
        for (int i = 0; i < geneCount; i++) {
            int maxValue = ranges[i].getEnd();
            int minValue = ranges[i].getStart();
            genes[i] = rand.nextInt( maxValue- minValue + 1) + minValue;
        }
    }


    @Override
    public Object getGenes() {
        return genes;
    }

    @Override
    public int getGenesNum() {
        return genes.length;
    }

    @Override
    public void mutate(double mutationRate) {
        Random rand = new Random();
        for (int i = 0; i < genes.length; i++) {
            if (rand.nextDouble() < mutationRate) {
                int maxValue = ranges[i].getEnd();
                int minValue = ranges[i].getStart();
                genes[i] = rand.nextInt(maxValue - minValue + 1) + minValue;
            }
        }
    }

    @Override
    public Chromosome copy() {
        return new IntegerChromosome(genes.length, ranges);
    }

    @Override
    public String toString() {
        return "IntegerChromosome " + Arrays.toString(genes) + " | fitness=" + fitness;
    }
}
