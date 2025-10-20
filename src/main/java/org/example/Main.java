package org.example;

import org.example.Algorithms.GA.Factories.Chromosomes.ChromosomeFactory;
import org.example.Algorithms.GA.Factories.Fitnesses.*;
import org.example.Algorithms.GA.Factories.Selections.ISelectionFactory;
import org.example.Algorithms.GA.Factories.Selections.RouletteWheelSelectionFactory;
import org.example.Algorithms.GA.Factories.Selections.TournamentSelectionFactory;
import org.example.Algorithms.GA.chromosomes.*;
import org.example.Algorithms.GA.Factories.CrossOvers.CrossOverFactory;
import org.example.Algorithms.GA.Factories.CrossOvers.MultiCrossOverFactory;
import org.example.Algorithms.GA.Factories.CrossOvers.SingleCrossOverFactory;
import org.example.Algorithms.GA.Factories.CrossOvers.UniformCrossOverFactory;
import org.example.Algorithms.GA.replacements.ElitistStrategy;
import org.example.Algorithms.GA.replacements.GenerationalReplacement;
import org.example.Algorithms.GA.replacements.IReplacementStrategy;
import org.example.Algorithms.GA.replacements.SteadyStateReplacement;
import org.example.Algorithms.GA.selections.ISelection;
import org.example.case_studies.GA.GeneticAlgorithm;
import org.example.Algorithms.GA.crossovers.*;
import java.util.Map;
import java.util.Scanner;

public class Main {
     static void main(String[] args) {
        int populationsize = 50;
        double crossoverrate = .07;
        double mutationRate = .02;
        int generations = 10000;
        int numVariables = 4;

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

        Map<Integer, FitnessFactory> fitnessFactoryMap = Map.of(
                1, new Fitness1Factory(),
                2, new Fitness2Factory(),
                3, new Fitness3Factory(),
                4, new Fitness4Factory(),
                5, new Fitness5Factory()
        );

        FitnessFactory fitnessFactory = fitnessFactoryMap.getOrDefault(functionChoice, new Fitness2Factory());


        // 2. Choose Selection Method
        System.out.println("\n--- Selection Methods ---");
        System.out.println("1. Tournament Selection");
        System.out.println("2. Roulette Wheel Selection");
        System.out.print("Choose selection method (1-2): ");
        int selectionChoice = scanner.nextInt();

       Map<Integer, ISelectionFactory> selectionFactoryMap = Map.of(
         1, new TournamentSelectionFactory(3),
         2, new RouletteWheelSelectionFactory()
       );

        ISelectionFactory selectionFactory = selectionFactoryMap.getOrDefault(selectionChoice, new RouletteWheelSelectionFactory());
        int tournamentSize = 3;
        ISelection selection;
        if (selectionFactory instanceof TournamentSelectionFactory)
        {
            System.out.print("Choose tournament size: ");
            tournamentSize = scanner.nextInt();
            tournamentSize = Math.max(tournamentSize,1);
            tournamentSize = Math.min(tournamentSize,50);
            selection = selectionFactory.create(tournamentSize);
        }
        else
        {
            selection = selectionFactory.create();
        }
        // Configure Ranges
        Range[] ranges = new Range[4];
        for (int i = 0; i < 4; i++) {
            double st,end;
            System.out.println("Enter range of variable ("+(i+1)+"): ");
            st = scanner.nextDouble();
            end = scanner.nextDouble();
            boolean valid = Range.checkRange(st, end);
            if (!valid) {
                i--;
                System.out.println("Invalid range of variable ("+(i+1)+").");
                continue;
            }
            ranges[i] = new Range(st, end);
        }
        // 3. Choose Chromosome Type
        System.out.println("\n--- Chromosome Types ---");
        System.out.println("1. Binary Chromosome");
        System.out.println("2. Floating Point Chromosome");
        System.out.println("3. Integer Chromosome");
        System.out.print("Choose chromosome type (1-3): ");
        int chromosomeChoice = scanner.nextInt();

        ChromosomeFactory chromosomeFactory;

        Map<Integer, ChromosomeFactory> factoryMap = Map.of(
                1, new BinaryChromosome(numVariables, ranges),
                2, new FloatingPointChromosome(numVariables, ranges),
                3, new IntegerChromosome(numVariables, ranges)
        );
        // binary chromosomes is the default
        chromosomeFactory = factoryMap.getOrDefault(chromosomeChoice, new BinaryChromosome(numVariables, ranges));


        // 4. Choose Crossover Algorithm
        System.out.println("\n--- Crossover Algorithms ---");
        System.out.println("1. Single Point Crossover");
        System.out.println("2. Multi Point Crossover");
        System.out.println("3. Uniform Crossover");
        System.out.print("Choose crossover algorithm (1-3): ");
        int crossoverChoice = scanner.nextInt();
         Map<Integer, CrossOverFactory> crossoverFactoryMap = Map.of(
                 1, new SingleCrossOverFactory(),
                 2, new MultiCrossOverFactory(),
                 3, new UniformCrossOverFactory()
         );

         CrossOverFactory factory = crossoverFactoryMap.getOrDefault(crossoverChoice, new SingleCrossOverFactory());
         ICrossOver crossover;

        // we tried to cancel if conditions
        if (factory instanceof MultiCrossOverFactory) {
            System.out.print("Enter number of crossover points: ");
            int points = scanner.nextInt();
            crossover = factory.createCrossOver(points);
        } else {
            crossover = factory.createCrossOver();
        }
        System.out.println("\n--- Replacement Strategies ---");
        System.out.println("1. Generational Replacement (GGA)  : Entire population is replaced each generation with offspring.");
        System.out.println("2. Steady-State Replacement (SSGA) : Randomly select a few parents to reproduce; offspring replace only the selected parents, others remain.");
        System.out.println("3. Elitist Strategy (Elitism)      : Similar to steady-state, but always keep the best individuals from previous generations.");
        System.out.print("Choose replacement strategy (1-3): ");
        int replacementChoice = scanner.nextInt();
        Map<Integer, IReplacementStrategy> replacementMap = Map.of(
                1, new GenerationalReplacement(),
                2, new SteadyStateReplacement(),
                3, new ElitistStrategy()
        );

        IReplacementStrategy replacementStrategy = replacementMap.getOrDefault(replacementChoice, new GenerationalReplacement());
        System.out.println("Enter selection size");
        int selectionSize = scanner.nextInt();



        System.out.println("Enter ranges of variables: ");

        // Create and configure Genetic Algorithm
        GeneticAlgorithm ga = new GeneticAlgorithm();
        ga.setPopulationSize(populationsize);
        ga.setFitnessFunction(fitnessFactory);
        ga.setCrossoverRate(crossoverrate);
        ga.setMutationRate(mutationRate);
        ga.setGenerations(generations);
        ga.setSelection(selection);
        ga.setChromosomeConfig(numVariables, ranges);
        ga.setChromosomeFactory(chromosomeFactory);
        ga.setCrossover(crossover);
        ga.setReplacementStrategy(replacementStrategy,selectionSize);

        System.out.println("\n=== Starting Genetic Algorithm ===");
        System.out.println("Configuration Summary:");
        System.out.println("- Fitness Function: " + fitnessFactory.getName());
        System.out.println("- Selection Method: " + selection.getClass().getSimpleName());
        System.out.println("- Chromosome Type: " + chromosomeFactory.getChromosomeType());
        System.out.println("- Crossover Algorithm: " + crossover.getClass().getSimpleName());
        System.out.println("- Population Size: " + populationsize);
        System.out.println("- Generations: " + generations);
        System.out.println("- Crossover Rate: " + crossover);
        System.out.println("- Mutation Rate: " + mutationRate);
        System.out.println("- Number of Variables: " + numVariables);

        ga.run();

        scanner.close();
    }
}