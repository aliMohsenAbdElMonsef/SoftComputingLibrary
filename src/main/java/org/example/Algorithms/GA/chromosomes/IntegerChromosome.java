package org.example.Algorithms.GA.chromosomes;

import java.util.Arrays;
import java.util.Random;

public class IntegerChromosome extends Chromosome {
    private int[] genes;
    private Range[] ranges;

    public IntegerChromosome(int geneCount, Range[] ranges) {
        this.ranges = ranges;
        this.genes = new int[geneCount];
        Random rand = new Random();
        for (int i = 0; i < geneCount; i++) {
            int maxValue = (int) ranges[i].getEnd();
            int minValue = (int) ranges[i].getStart();
            genes[i] = rand.nextInt(maxValue - minValue + 1) + minValue;
        }
    }

    @Override
    public Object getGenes() {
        return genes;
    }

    @Override
    public void setGenes(Object genes) {
        int[] _genes = (int[]) genes;
        this.genes = _genes.clone();
    }

    @Override
    public int getGenesNum() {
        return genes.length;
    }

    @Override
    public Chromosome[] crossover(Chromosome parent2, int point) {
        if (!(parent2 instanceof IntegerChromosome p2))
            throw new IllegalArgumentException("Parent must be IntegerChromosome");

        int[] g1 = (int[]) this.getGenes();
        int[] g2 = (int[]) p2.getGenes();

        int[] newG1 = new int[g1.length];
        int[] newG2 = new int[g2.length];

        // Copy genes before crossover point from parent1
        System.arraycopy(g1, 0, newG1, 0, point);
        System.arraycopy(g2, 0, newG2, 0, point);

        // Copy genes after crossover point from parent2
        System.arraycopy(g2, point, newG1, point, g1.length - point);
        System.arraycopy(g1, point, newG2, point, g2.length - point);

        IntegerChromosome child1 = (IntegerChromosome) this.copy();
        IntegerChromosome child2 = (IntegerChromosome) p2.copy();

        child1.setGenes(newG1);
        child2.setGenes(newG2);

        return new Chromosome[]{child1, child2};
    }

    @Override
    public void mutate(double mutationRate) {
        Random rand = new Random();
        for (int i = 0; i < genes.length; i++) {
            if (rand.nextDouble() < mutationRate) {
                int maxValue = (int) ranges[i].getEnd();
                int minValue = (int) ranges[i].getStart();
                genes[i] = rand.nextInt(maxValue - minValue + 1) + minValue;
            }
        }
    }

    @Override
    public Chromosome copy() {
        IntegerChromosome copy = new IntegerChromosome(genes.length, ranges);
        copy.genes = this.genes.clone();
        copy.fitness = this.fitness;
        return copy;
    }

    @Override
    public String toString() {
        return "Fitness=" + fitness + ", Genes=" + Arrays.toString(genes);
    }
}