package org.example.Algorithms.GA.chromosomes;

public abstract class Chromosome implements Comparable<Chromosome> {
    protected double fitness;

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public abstract Object getGenes();
    public abstract int getGenesNum();
    @Override
    public int compareTo(Chromosome o) {
        return Double.compare(o.fitness, this.fitness);
    }
    public abstract Chromosome[] crossover(Chromosome parent2, int point);
    public abstract void mutate(double mutationRate);

    public abstract Chromosome copy();
    public abstract void setGenes(Object genes);

    @Override
    public String toString() {
        return STR."Fitness=\{fitness}, Genes=\{getGenes()}";
    }
}

