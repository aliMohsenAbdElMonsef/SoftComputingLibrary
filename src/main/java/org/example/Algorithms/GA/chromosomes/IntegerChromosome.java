package org.example.Algorithms.GA.chromosomes;

import java.util.Arrays;
import java.util.Random;

public class IntegerChromosome extends Chromosome<int[]> {
    private static final Random rand = new Random();

    private int[] genes;
    private final Range[] ranges;

    public IntegerChromosome(int geneCount, Range[] ranges) {
        this.ranges = Arrays.copyOf(ranges, ranges.length);
        this.genes = new int[geneCount];

        for (int i = 0; i < geneCount; i++) {
            int min = this.ranges[i].getStart();
            int max = this.ranges[i].getEnd();
            genes[i] = rand.nextInt(max - min + 1) + min;
        }
    }

    @Override
    public int[] getGenes() {
        return genes;
    }

    @Override
    public void setGenes(int[] genes) {
        this.genes = genes;
    }

    @Override
    public int getGenesNum() {
        return genes.length;
    }

    @Override
    public void mutate(double mutationRate) {
        for (int i = 0; i < genes.length; i++) {
            if (rand.nextDouble() < mutationRate) {
                int min = ranges[i].getStart();
                int max = ranges[i].getEnd();
                genes[i] = rand.nextInt(max - min + 1) + min;
            }
        }
    }

    @Override
    public Chromosome<int[]> copy() {
        IntegerChromosome copy = new IntegerChromosome(genes.length, ranges);
        copy.setGenes(genes.clone());
        copy.fitness = this.fitness;
        return copy;
    }

    @Override
    public void swapGene(Chromosome<int[]> c, int index) {
        int[] g1 = this.getGenes();
        int[] g2 = c.getGenes();
        int temp = g1[index];
        g1[index] = g2[index];
        g2[index] = temp;
    }

    @Override
    public String toString() {
        return "IntegerChromosome " + Arrays.toString(genes) + " | fitness=" + fitness;
    }
}
