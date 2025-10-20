package org.example.Algorithms.GA.Factories.Selections;

import org.example.Algorithms.GA.selections.ISelection;
import org.example.Algorithms.GA.selections.RouletteWheelSelection;

public class RouletteWheelSelectionFactory implements ISelectionFactory {
    @Override
    public ISelection create(Object... params) {
        return new RouletteWheelSelection();
    }
}
