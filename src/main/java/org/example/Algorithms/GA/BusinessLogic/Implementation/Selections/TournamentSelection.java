package org.example.Algorithms.GA.BusinessLogic.Implementation.Selections;

import org.example.Algorithms.GA.BusinessLogic.Contracts.Chromosomes.Chromosome;
import org.example.Algorithms.GA.BusinessLogic.Contracts.Selections.ISelection;

import java.util.List;
import java.util.Random;

public class TournamentSelection implements ISelection {
    private int tournamentSize;
    private Random rand;

    public TournamentSelection(int tournamentSize) {
        this.tournamentSize = tournamentSize;
        this.rand = new Random();
    }

    @Override
    public Chromosome select(List<? extends Chromosome> population) {
        if (population == null || population.isEmpty()) {
            throw new IllegalArgumentException("Population cannot be null or empty");
        }

        Chromosome best = population.get(rand.nextInt(population.size()));

        for (int i = 1; i < tournamentSize; i++) {
            Chromosome challenger = population.get(rand.nextInt(population.size()));
            if (challenger.getFitness() > best.getFitness()) {
                best = challenger;
            }
        }

        return best;
    }

    public int getTournamentSize() {
        return tournamentSize;
    }

    public void setTournamentSize(int tournamentSize) {
        if (tournamentSize < 1) {
            throw new IllegalArgumentException("Tournament size must be at least 1");
        }
        this.tournamentSize = tournamentSize;
    }
}