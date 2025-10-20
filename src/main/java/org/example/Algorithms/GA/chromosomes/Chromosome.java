package org.example.Algorithms.GA.chromosomes;

public abstract class Chromosome<G> implements Comparable<Chromosome<G>> {
    protected double fitness;
    public  Chromosome(int chromosomeLength, Range[] ranges){

    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }
    public abstract double[] decode();
    public abstract G getGenes();
    public abstract void setGenes(G genes);
    public abstract int getGenesNum();
    public abstract void mutate(double mutationRate);
    public abstract Chromosome<G> copy();
    public abstract void swapGene(Chromosome<G> c, int index);
    @Override
    public int compareTo(Chromosome<G> o) {
        return Double.compare(o.fitness, this.fitness);
    }

    @Override
    public String toString() {
        return "Fitness=" + fitness + ", Genes=" + getGenes();
    }
}
