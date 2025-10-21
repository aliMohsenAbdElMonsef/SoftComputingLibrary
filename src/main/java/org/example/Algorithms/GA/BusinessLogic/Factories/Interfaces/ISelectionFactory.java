package org.example.Algorithms.GA.BusinessLogic.Factories.Interfaces;

import org.example.Algorithms.GA.BusinessLogic.Contracts.Selections.ISelection;

public interface ISelectionFactory {
    ISelection create(Object ... params);
}
