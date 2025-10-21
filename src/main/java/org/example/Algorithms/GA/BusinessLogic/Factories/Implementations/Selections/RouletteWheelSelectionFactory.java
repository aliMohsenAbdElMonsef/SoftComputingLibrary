package org.example.Algorithms.GA.BusinessLogic.Factories.Implementations.Selections;

import org.example.Algorithms.GA.BusinessLogic.Contracts.Selections.ISelection;
import org.example.Algorithms.GA.BusinessLogic.Factories.Interfaces.ISelectionFactory;
import org.example.Algorithms.GA.BusinessLogic.Implementation.Selections.RouletteWheelSelection;

public class RouletteWheelSelectionFactory implements ISelectionFactory {
    @Override
    public ISelection create(Object... params) {
        return new RouletteWheelSelection();
    }
}
