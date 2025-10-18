package org.example.case_studies.GA;

import org.example.case_studies.GA.functions.Functions;
import org.example.Algorithms.GA.chromosomes.BinaryChromosome;
import org.example.Algorithms.GA.chromosomes.Chromosome;
import org.example.Algorithms.GA.chromosomes.Range;
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

    private List<BinaryChromosome> population;

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

    public void setSelection(ISelection selection) {
        this.selection = selection;
    }

    public void run() {
        population = new ArrayList<>();
        for (int i = 0; i < populationSize; i++) {
            population.add(new BinaryChromosome(numVariables, chromosomeLength, ranges));
        }

        BinaryChromosome best = null;
        BinaryChromosome worst = null;

        for (int gen = 0; gen < generations; gen++) {
            for (BinaryChromosome c : population) {
                double[] x = c.decode();
                double f = fitnessFunction.method(x[0], x[1], x[2], x[3]);
                c.setFitness(f);
            }

            population.sort(Comparator.comparingDouble(BinaryChromosome::getFitness));
            worst = population.get(0);
            best = population.get(population.size() - 1);

            System.out.printf("Generation %d | Best Fitness: %.5f | Worst Fitness: %.5f%n",
                    gen, best.getFitness(), worst.getFitness());

            List<BinaryChromosome> newPop = new ArrayList<>();
            Random rand = new Random();

            while (newPop.size() < populationSize) {
                BinaryChromosome parent1 = (BinaryChromosome) selection.select(population);
                BinaryChromosome parent2 = (BinaryChromosome) selection.select(population);

                BinaryChromosome child1, child2;
                if (rand.nextDouble() < crossoverRate) {
                    int point = rand.nextInt(parent1.getGenesNum());
                    Chromosome[] offspring = parent1.crossover(parent2, point);
                    child1 = (BinaryChromosome) offspring[0];
                    child2 = (BinaryChromosome) offspring[1];
                } else {
                    child1 = (BinaryChromosome) parent1.copy();
                    child2 = (BinaryChromosome) parent2.copy();
                }

                child1.mutate(mutationRate);
                child2.mutate(mutationRate);
                newPop.add(child1);
                if (newPop.size() < populationSize) newPop.add(child2);
            }

            population = newPop;
        }

        System.out.println("\n===== RESULTS =====");
        System.out.println("Best Chromosome: " + best);
        System.out.println("Worst Chromosome: " + worst);
    }
}