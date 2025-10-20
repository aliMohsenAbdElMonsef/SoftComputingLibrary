package org.example;

import org.example.Algorithms.GA.chromosomes.Range;
import org.example.Algorithms.GA.chromosomes.BinaryChromosome;
import org.example.Algorithms.GA.chromosomes.FloatingPointChromosome;
import org.example.Algorithms.GA.chromosomes.IntegerChromosome;
import org.example.case_studies.GA.GeneticAlgorithm;
import org.example.case_studies.GA.functions.*;
import org.example.Algorithms.GA.selection.methods.*;
import org.example.Algorithms.GA.crossovers.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Genetic Algorithm Configuration ===");
        System.out.println("Please choose from the following options:\n");

        // 1. Choose Fitness Function
        System.out.println("--- Fitness Functions ---");
        System.out.println("1. Function1: sin(x1) + cos(x2) + x3² - sqrt(x4)");
        System.out.println("2. Function2: sin(x1*x2) + cos(x3) + 0.5*x4²");
        System.out.println("3. Function3: x1 + x2² + x3³ + x4⁴");
        System.out.println("4. Function4: sin(x1)*cos(x2) + sqrt(x3)*x4²");
        System.out.println("5. Function5: sin(sin(x1)*cos(sqrt(x2))) + sin(x3*x4)");
        System.out.print("Choose fitness function (1-5): ");
        int functionChoice = scanner.nextInt();

        Functions fitnessFunction;
        switch (functionChoice) {
            case 1: fitnessFunction = new Function1(); break;
            case 2: fitnessFunction = new Function2(); break;
            case 3: fitnessFunction = new Function3(); break;
            case 4: fitnessFunction = new Function4(); break;
            case 5: fitnessFunction = new Function5(); break;
            default:
                System.out.println("Invalid choice, using Function2 as default.");
                fitnessFunction = new Function2();
        }

        // 2. Choose Selection Method
        System.out.println("\n--- Selection Methods ---");
        System.out.println("1. Tournament Selection");
        System.out.println("2. Roulette Wheel Selection");
        System.out.print("Choose selection method (1-2): ");
        int selectionChoice = scanner.nextInt();

        ISelection selection;
        if (selectionChoice == 1) {
            System.out.print("Enter tournament size: ");
            int tournamentSize = scanner.nextInt();
            selection = new TournamentSelection(tournamentSize);
        } else {
            selection = new RouletteWheelSelection();
        }

        // 3. Choose Chromosome Type
        System.out.println("\n--- Chromosome Types ---");
        System.out.println("1. Binary Chromosome");
        System.out.println("2. Floating Point Chromosome");
        System.out.println("3. Integer Chromosome");
        System.out.print("Choose chromosome type (1-3): ");
        int chromosomeChoice = scanner.nextInt();

        Class<? extends org.example.Algorithms.GA.chromosomes.Chromosome> chromosomeType;
        switch (chromosomeChoice) {
            case 1: chromosomeType = BinaryChromosome.class; break;
            case 2: chromosomeType = FloatingPointChromosome.class; break;
            case 3: chromosomeType = IntegerChromosome.class; break;
            default:
                System.out.println("Invalid choice, using Binary Chromosome as default.");
                chromosomeType = BinaryChromosome.class;
        }

        // 4. Choose Crossover Algorithm
        System.out.println("\n--- Crossover Algorithms ---");
        System.out.println("1. Single Point Crossover");
        System.out.println("2. Multi Point Crossover");
        System.out.println("3. Uniform Crossover");
        System.out.print("Choose crossover algorithm (1-3): ");
        int crossoverChoice = scanner.nextInt();

        ICrossOver crossover;
        switch (crossoverChoice) {
            case 1: crossover = new SingleCrossOver(); break;
            case 2: crossover = new MultiCrossOver(); break;
            case 3: crossover = new UniformCrossOver(); break;
            default:
                System.out.println("Invalid choice, using Single Point Crossover as default.");
                crossover = new SingleCrossOver();
        }

        // 5. Get Algorithm Parameters
        System.out.println("\n--- Algorithm Parameters ---");
        System.out.print("Enter population size: ");
        int populationSize = scanner.nextInt();

        System.out.print("Enter number of generations: ");
        int generations = scanner.nextInt();

        System.out.print("Enter crossover rate (0.0-1.0): ");
        double crossoverRate = scanner.nextDouble();

        System.out.print("Enter mutation rate (0.0-1.0): ");
        double mutationRate = scanner.nextDouble();

        System.out.print("Enter number of variables: ");
        int numVariables = scanner.nextInt();

        System.out.print("Enter chromosome length (for binary): ");
        int chromosomeLength = scanner.nextInt();

        // Configure Ranges
        Range[] ranges = new Range[numVariables];
        for (int i = 0; i < numVariables; i++) {
            ranges[i] = new Range(0.0, 5.0);
        }

        // Create and configure Genetic Algorithm
        GeneticAlgorithm ga = new GeneticAlgorithm();
        ga.setPopulationSize(populationSize);
        ga.setFitnessFunction(fitnessFunction);
        ga.setCrossoverRate(crossoverRate);
        ga.setMutationRate(mutationRate);
        ga.setGenerations(generations);
        ga.setSelection(selection);
        ga.setChromosomeConfig(numVariables, chromosomeLength, ranges);
        ga.setChromosomeType(chromosomeType);
        ga.setCrossover(crossover);

        System.out.println("\n=== Starting Genetic Algorithm ===");
        System.out.println("Configuration Summary:");
        System.out.println("- Fitness Function: " + fitnessFunction.getClass().getSimpleName());
        System.out.println("- Selection Method: " + selection.getClass().getSimpleName());
        System.out.println("- Chromosome Type: " + chromosomeType.getSimpleName());
        System.out.println("- Crossover Algorithm: " + crossover.getClass().getSimpleName());
        System.out.println("- Population Size: " + populationSize);
        System.out.println("- Generations: " + generations);
        System.out.println("- Crossover Rate: " + crossoverRate);
        System.out.println("- Mutation Rate: " + mutationRate);
        System.out.println("- Number of Variables: " + numVariables);
        System.out.println("- Chromosome Length: " + chromosomeLength);

        // Run the algorithm
        ga.run();

        scanner.close();
    }
}