package org.example.Algorithms.GA.chromosomes;

import java.util.Random;

public class FloatingPointChromosome extends Chromosome {
    private double[] genes;
    private Range[] ranges;
    private int numberOfVariables;

    public FloatingPointChromosome(int numVariables, Range[] _ranges) {
        this.ranges = _ranges;
        this.numberOfVariables = numVariables;

        Random rand = new Random();
        genes = new double[numVariables];

        for (int i = 0; i < numberOfVariables; i++) {
            double minValue = ranges[i].getStart();
            double maxValue = ranges[i].getEnd();
            genes[i] = rand.nextDouble() * (maxValue - minValue) + minValue;
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
    public Chromosome[] crossover(Chromosome parent2, int point) {
        if (!(parent2 instanceof FloatingPointChromosome p2))
            throw new IllegalArgumentException("Parent must be BinaryChromosome");

        Chromosome child1 = this.copy();
        Chromosome child2 = parent2;

        double[] c1Genes = (double[]) this.getGenes();
        double[] c2Genes = (double[]) parent2.getGenes();

        return new Chromosome[]{child1, child2};
    }

    @Override
    public void mutate(double mutationRate) {
        Random rand = new Random();

        for (int i = 0; i < numberOfVariables; i++) {
            if (rand.nextDouble() < mutationRate) {
                double minValue = ranges[i].getStart();
                double maxValue = ranges[i].getEnd();
                double mutationAmount = (rand.nextDouble() - 0.5) * (maxValue - minValue) * 0.1;
                genes[i] += mutationAmount;
                if (genes[i] < minValue) genes[i] = minValue;
                if (genes[i] > maxValue) genes[i] = maxValue;
            }
        }
    }

    @Override
    public Chromosome copy() {
        FloatingPointChromosome copy = new FloatingPointChromosome(numberOfVariables, ranges);
        copy.genes = this.genes.clone();
        copy.fitness = this.fitness;
        return copy;
    }

    @Override
    public void setGenes(Object genes) {
        double[] newGenes = (double[]) genes;
        this.genes = newGenes;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Fitness=").append(fitness).append(", Genes=[");
        for (int i = 0; i < genes.length; i++) {
            sb.append(String.format("%.3f", genes[i]));
            if (i < genes.length - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}
