package org.example.Algorithms.GA.BusinessLogic.Factories.Implementations.Selections;


import org.example.Algorithms.GA.BusinessLogic.Contracts.Selections.ISelection;
import org.example.Algorithms.GA.BusinessLogic.Factories.Interfaces.ISelectionFactory;
import org.example.Algorithms.GA.BusinessLogic.Implementation.Selections.TournamentSelection;

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
