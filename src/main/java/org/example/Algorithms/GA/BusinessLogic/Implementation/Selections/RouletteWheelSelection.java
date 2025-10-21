package org.example.Algorithms.GA.BusinessLogic.Implementation.Selections;

import org.example.Algorithms.GA.BusinessLogic.Contracts.Chromosomes.Chromosome;
import org.example.Algorithms.GA.BusinessLogic.Contracts.Selections.ISelection;

import java.util.List;
import java.util.Random;

public class RouletteWheelSelection implements ISelection {
    private Random rand;

    public RouletteWheelSelection() {
        this.rand = new Random();
    }

    @Override
    public Chromosome select(List<? extends Chromosome> population) {
        if (population == null || population.isEmpty()) {
            throw new IllegalArgumentException("Population cannot be null or empty");
        }

        double totalFitness = 0;
        double minFitness = Double.MAX_VALUE;

        for (Chromosome chromosome : population) {
            if (chromosome.getFitness() < minFitness) {
                minFitness = chromosome.getFitness();
            }
        }

        double offset = minFitness < 0 ? Math.abs(minFitness) : 0;

        for (Chromosome chromosome : population) {
            totalFitness += chromosome.getFitness() + offset;
        }

        if (totalFitness == 0) {
            return population.get(rand.nextInt(population.size()));
        }

        double randomValue = rand.nextDouble() * totalFitness;
        double cumulativeFitness = 0;

        for (Chromosome chromosome : population) {
            cumulativeFitness += chromosome.getFitness() + offset;
            if (cumulativeFitness >= randomValue) {
                return chromosome;
            }
        }

        return population.get(population.size() - 1);
    }
}