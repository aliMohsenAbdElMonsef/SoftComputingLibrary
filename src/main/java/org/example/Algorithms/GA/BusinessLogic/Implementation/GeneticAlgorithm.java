package org.example.Algorithms.GA.BusinessLogic.Implementation;

import org.example.Algorithms.GA.BusinessLogic.Contracts.Chromosomes.Chromosome;
import org.example.Algorithms.GA.BusinessLogic.Implementation.Chromosomes.Range;
import org.example.Algorithms.GA.BusinessLogic.Factories.Interfaces.ChromosomeFactory;
import org.example.Algorithms.GA.BusinessLogic.Contracts.CrossOvers.ICrossOver;
import org.example.Algorithms.GA.BusinessLogic.Contracts.Replacements.IReplacementStrategy;
import org.example.Algorithms.GA.BusinessLogic.Implementation.Selections.RouletteWheelSelection;
import org.example.Algorithms.GA.BusinessLogic.Contracts.Functions.Functions;
import org.example.Algorithms.GA.BusinessLogic.Contracts.Selections.ISelection;

import java.util.*;

public class GeneticAlgorithm {
    private int populationSize;//done
    private int generations;//done
    private int selectionSize;
    private double crossoverRate;//done
    private double mutationRate;//done
    private Functions fitnessFunction;//done
    private Range[] ranges;//done
    private int numVariables;//dene
    private ISelection selection;//done
    private ChromosomeFactory chromosomeFactory;//done
    private ICrossOver crossover;
    private IReplacementStrategy replacementStrategy;
    private List<Chromosome> population;
    private String ChromosomeType;
    public GeneticAlgorithm() {
        this.selection = new RouletteWheelSelection();
    }

    public void setPopulationSize(int populationSize) {
        this.populationSize = populationSize;
    }

    public void setGenerations(int generations) {

        this.generations = generations;
    }
    public void setCrossoverRate(double crossoverRate) {
        this.crossoverRate = crossoverRate;
    }
    public void setMutationRate(double mutationRate) {
        this.mutationRate = mutationRate;
    }
    public void setFitnessFunction(Functions fitnessFunction) {

        this.fitnessFunction = fitnessFunction;
    }
    public void setChromosomeConfig(int numVariables, Range[] ranges) {
        this.numVariables = numVariables;
        // validate and possibly adjust ranges according to the fitness function
        if (this.fitnessFunction != null) {
            this.ranges = this.fitnessFunction.validateInput(ranges);
        } else {
            this.ranges = ranges;
        }
    }
    public void setChromosomeFactory(ChromosomeFactory factory) {
        this.chromosomeFactory = factory;
    }
    public void setSelection(ISelection selection) {

        this.selection = selection;
    }
    public void setReplacementStrategy(IReplacementStrategy replacementStrategy, int selectionSize) {
        this.replacementStrategy = replacementStrategy;
        this.selectionSize = selectionSize;
    }

    public void setCrossover(ICrossOver crossover) {
        this.crossover = crossover;
    }

    public void run() {
        population = new ArrayList<>();
        for (int i = 0; i < populationSize; i++) {
            Chromosome c = chromosomeFactory.create(numVariables, ranges);
            population.add(c);
        }

        Chromosome best = null;
        Chromosome worst = null;

        Chromosome generalbest = null;
        Chromosome generalworst = null;

        for (int gen = 0; gen < generations; gen++) {
            for (Chromosome c : population) {
                double[] x  = decodeChromosome(c);
                double f = fitnessFunction.method(x[0], x[1], x[2], x[3]);
                c.setFitness(f);
            }
            List<Chromosome> selectedPortion = replacementStrategy.selectForReproduction(population,selectionSize);
            selectedPortion.sort(Comparator.comparingDouble(Chromosome::getFitness));
            worst = selectedPortion.get(0);
            best = selectedPortion.get(selectedPortion.size() - 1);
            if(generalbest == null){
                generalbest = best;
            }
            else{
                if(best.getFitness() > generalbest.getFitness())
                {
                    generalbest = best;
                }
            }
            if(generalworst == null){
                generalworst = worst;
            }
            else{
                if(worst.getFitness() < generalworst.getFitness())
                {
                    generalworst = worst;
                }
            }
            System.out.printf("Generation %d | Best Fitness: %.5f | Worst Fitness: %.5f%n",
                    gen, best.getFitness(), worst.getFitness());

            List<Chromosome> offSprings = new ArrayList<>();
            Random rand = new Random();

            while (offSprings.size() < selectedPortion.size()) {
                Chromosome parent1 = selection.select(selectedPortion);
                Chromosome parent2 = selection.select(selectedPortion);

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
                offSprings.add(child1);
                if (offSprings.size() < selectedPortion.size()) offSprings.add(child2);
            }

            replacementStrategy.Replace(population,selectedPortion,offSprings);
        }

        System.out.println("\n===== RESULTS =====");
        System.out.println("Chromosome Type: " + this.chromosomeFactory.getChromosomeType());
        System.out.println("Best Chromosome: " + generalbest);
        System.out.println("Worst Chromosome: " + generalworst);
    }
    private double[] decodeChromosome(Chromosome chromosome) {
        return chromosome.decode();
    }
}