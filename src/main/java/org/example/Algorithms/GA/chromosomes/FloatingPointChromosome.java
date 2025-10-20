package org.example.Algorithms.GA.chromosomes;

import org.example.Algorithms.GA.Factories.Chromosomes.ChromosomeFactory;

import java.util.Random;
import java.util.Arrays;

public class FloatingPointChromosome extends Chromosome<double[]> implements ChromosomeFactory<double[]> {
    private static final Random rand = new Random();

    private double[] genes;
    private final Range[] ranges;

    public FloatingPointChromosome(int numberOfVariables, Range[] ranges) {
        super(numberOfVariables,ranges);
        this.ranges = Arrays.copyOf(ranges, ranges.length);
        this.genes = new double[numberOfVariables];

        for (int i = 0; i < numberOfVariables; i++) {
            double min = this.ranges[i].getStart();
            double max = this.ranges[i].getEnd();
            genes[i] = rand.nextDouble() * (max - min) + min;
        }
    }

    @Override
    public double[] getGenes() {
        return genes;
    }

    @Override
    public void setGenes(double[] genes) {
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
                double min = ranges[i].getStart();
                double max = ranges[i].getEnd();
                double mutationAmount = (rand.nextDouble() - 0.5) * (max - min) * 0.1;
                genes[i] += mutationAmount;
                if (genes[i] < min) genes[i] = min;
                if (genes[i] > max) genes[i] = max;
            }
        }
    }

    @Override
    public Chromosome<double[]> copy() {
        FloatingPointChromosome copy = new FloatingPointChromosome(genes.length, ranges);
        copy.setGenes(genes.clone());
        copy.fitness = this.fitness;
        return copy;
    }

    @Override
    public void swapGene(Chromosome<double[]> c, int index) {
        double[] g1 = this.getGenes();
        double[] g2 = c.getGenes();
        double temp = g1[index];
        g1[index] = g2[index];
        g2[index] = temp;
    }
    @Override
    public double[] decode() {
        double[] genes = this.getGenes();
        return genes;
    }
    @Override
    public String toString() {
        return "FloatingPointChromosome Fitness=" + fitness + ", Genes=" + Arrays.toString(genes);
    }

    @Override
    public Chromosome<double[]> create(int ChromosomeLength, Range[] ranges) {
        return new FloatingPointChromosome(ChromosomeLength, ranges);
    }

    @Override
    public String getChromosomeType() {
        return "Floating Point";
    }
}
