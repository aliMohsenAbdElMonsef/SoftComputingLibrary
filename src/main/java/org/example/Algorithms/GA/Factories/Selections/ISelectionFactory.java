package org.example.Algorithms.GA.Factories.Selections;

import org.example.Algorithms.GA.selections.ISelection;

public interface ISelectionFactory {
    ISelection create(Object ... params);
}
