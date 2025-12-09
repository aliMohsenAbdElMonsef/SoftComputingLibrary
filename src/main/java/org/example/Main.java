package org.example;

import org.example.Algorithms.GA.ApplicationLayer.GAConfig.GAConfig;
import org.example.Algorithms.GA.Presentation.GArunner.GARunner;
import org.example.Algorithms.GA.Presentation.GAui.GAUI;
import org.example.Algorithms.FuzzyLogic.FuzzyLogicCLI;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select Algorithm:");
        System.out.println("1. Genetic Algorithm");
        System.out.println("2. Fuzzy Logic");
        System.out.print("Enter choice (1 or 2): ");

        int choice = 0;
        if (scanner.hasNextInt()) {
            choice = scanner.nextInt();
            scanner.nextLine();
        } else {
            scanner.nextLine();
        }

        if (choice == 1) {
             GAUI ui = new GAUI();
             GAConfig config = ui.collectUserInput();
             GARunner runner = new GARunner();
             runner.run(config);
        } else if (choice == 2) {
             FuzzyLogicCLI cli = new FuzzyLogicCLI();
             cli.run();
        } else {
            System.out.println("Invalid choice. Exiting.");
        }
    }
}