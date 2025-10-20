package org.example.Algorithms.GA.replacements;

public class HelpingMethods {
    public static void checkSelectedElements(int selectedElements){
        if (selectedElements % 2 != 0) {
            selectedElements++;
        }
        selectedElements = Math.min(selectedElements, 50);
        selectedElements = Math.max(selectedElements, 0);
    }
}
