package org.example.Algorithms.GA.Factories.Selections;


import org.example.Algorithms.GA.selections.ISelection;
import org.example.Algorithms.GA.selections.TournamentSelection;

public class TournamentSelectionFactory implements ISelectionFactory {
    private int tournamentSize;

    public TournamentSelectionFactory(int tournamentSize) {
        this.tournamentSize = tournamentSize;
    }

    @Override
    public ISelection create(Object ... params) {
        if (params.length <= 0)
        {
            return new TournamentSelection(tournamentSize);
        }
        else{
            return new TournamentSelection((int)params[0]);
        }
    }
}
