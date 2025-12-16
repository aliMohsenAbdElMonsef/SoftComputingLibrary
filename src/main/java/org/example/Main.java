package org.example;

import org.example.Algorithms.GA.ApplicationLayer.GAConfig.GAConfig;
import org.example.Algorithms.GA.Presentation.GArunner.GARunner;
import org.example.Algorithms.GA.Presentation.GAui.GAUI;
import org.example.Algorithms.FuzzyLogic.FuzzyLogicCLI;
import org.example.Algorithms.NN.Engine.NNEngine;
import org.example.Algorithms.NN.InputProviders.CLIInputProvider;
import org.example.Algorithms.NN.InputProviders.FileInputProvider;
import org.example.Algorithms.NN.InputProviders.InputProvider;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select Algorithm:");
        System.out.println("1. Genetic Algorithm");
        System.out.println("2. Fuzzy Logic");
        System.out.println("3. Neural Networks");
        System.out.print("Enter choice (1 or 2 or 3): ");

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
        } else if(choice == 3) {
                try {
                    Scanner sc = new Scanner(System.in);
                    System.out.print("Input source (cli/file): ");
                    String choice2 = sc.next();

                    InputProvider provider;

                    if (choice2.equalsIgnoreCase("file")) {
                        System.out.print("Config file path (.txt): ");
                        provider = new FileInputProvider(sc.next());
                    } else {
                        provider = new CLIInputProvider();
                    }

                    new NNEngine().run(provider);

                } catch (Exception e) {
                    System.err.println("Error: " + e.getMessage());
                    e.printStackTrace();
                }
        }
        else{
            System.out.println("Invalid choice. Exiting.");
        }
    }
}