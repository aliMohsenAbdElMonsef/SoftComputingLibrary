package org.example.case_studies.GA;

import org.example.Algorithms.GA.chromosomes.*;
import org.example.Algorithms.GA.crossovers.ICrossOver;
import org.example.case_studies.GA.functions.Functions;
import org.example.Algorithms.GA.selection.methods.ISelection;
import org.example.Algorithms.GA.selection.methods.TournamentSelection;

import java.util.*;

public class GeneticAlgorithm {
    private int populationSize;
    private int generations;
    private double crossoverRate;
    private double mutationRate;
    protected int chromosomeLength;
    private Functions fitnessFunction;
    private Range[] ranges;
    private int numVariables;
    private ISelection selection;
    private Class<? extends Chromosome> chromosomeType;
    private ICrossOver crossover;

    private List<Chromosome> population;

    public GeneticAlgorithm() {
        this.selection = new TournamentSelection();
    }

    public void setPopulationSize(int populationSize) {
        this.populationSize = populationSize;
    }


    public void setCrossoverRate(double crossoverRate) {
        this.crossoverRate = crossoverRate;
    }

    public void setMutationRate(double mutationRate) {
        this.mutationRate = mutationRate;
    }

    public void setGenerations(int generations) {
        this.generations = generations;
    }

    public void setFitnessFunction(Functions fitnessFunction) {
        this.fitnessFunction = fitnessFunction;
    }

    public void setChromosomeConfig(int numVariables, int chromosomeLength, Range[] ranges) {
        this.numVariables = numVariables;
        this.chromosomeLength = chromosomeLength;
        this.ranges = ranges;
    }

    public void setChromosomeType(Class<? extends Chromosome> chromosomeType) {
        this.chromosomeType = chromosomeType;
    }

    public void setSelection(ISelection selection) {
        this.selection = selection;
    }

    public void setCrossover(ICrossOver crossover) {
        this.crossover = crossover;
    }

    public void run() {
        population = new ArrayList<>();
        for (int i = 0; i < populationSize; i++) {
            if (chromosomeType == BinaryChromosome.class) {
                population.add(new BinaryChromosome(numVariables, chromosomeLength, ranges));
            }
            else if(chromosomeType == FloatingPointChromosome.class) {
                population.add(new FloatingPointChromosome(numVariables, ranges));
            } else if (chromosomeType == IntegerChromosome.class  ) {
                population.add(new IntegerChromosome(numVariables, ranges));
            }

        }

        Chromosome best = null;
        Chromosome worst = null;

        for (int gen = 0; gen < generations; gen++) {
            for (Chromosome c : population) {
                double[] x  = decodeChromosome(c);
                double f = fitnessFunction.method(x[0], x[1], x[2], x[3]);
                c.setFitness(f);
            }

            population.sort(Comparator.comparingDouble(Chromosome::getFitness));
            worst = population.get(0);
            best = population.get(population.size() - 1);

            System.out.printf("Generation %d | Best Fitness: %.5f | Worst Fitness: %.5f%n",
                    gen, best.getFitness(), worst.getFitness());

            List<Chromosome> newPop = new ArrayList<>();
            Random rand = new Random();

            while (newPop.size() < populationSize) {
                Chromosome parent1 = selection.select(population);
                Chromosome parent2 = selection.select(population);

                Chromosome child1, child2;
                if (rand.nextDouble() < crossoverRate) {
                    Chromosome[] offspring = crossover.apply(parent1, parent2);
                    child1 = offspring[0];
                    child2 = offspring[1];
                } else {
                    child1 = parent1.copy();
                    child2 = parent2.copy();
                }

                child1.mutate(mutationRate);
                child2.mutate(mutationRate);
                newPop.add(child1);
                if (newPop.size() < populationSize) newPop.add(child2);
            }

            population = newPop;
        }

        System.out.println("\n===== RESULTS =====");
        System.out.println("Chromosome Type: " + chromosomeType.getSimpleName());
        System.out.println("Best Chromosome: " + best);
        System.out.println("Worst Chromosome: " + worst);
    }

    private double[] decodeChromosome(Chromosome chromosome) {
        if (chromosome instanceof BinaryChromosome) {
            return ((BinaryChromosome) chromosome).decode();
        } else if (chromosome instanceof FloatingPointChromosome) {
            Object genes = chromosome.getGenes();
            if (genes instanceof double[]) {
                return (double[]) genes;
            }
        } else if (chromosome instanceof IntegerChromosome) {
            Object genes = chromosome.getGenes();
            if (genes instanceof int[]) {
                int[] intGenes = (int[]) genes;
                double[] doubleGenes = new double[intGenes.length];
                for (int i = 0; i < intGenes.length; i++) {
                    doubleGenes[i] = intGenes[i];
                }
                return doubleGenes;
            }
        }

        // return zeros if we can't decode
        return new double[numVariables];
    }
}