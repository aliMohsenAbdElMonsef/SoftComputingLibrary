package org.example.Algorithms.GA.Presentation.GAui;

import org.example.Algorithms.GA.BusinessLogic.Factories.Implementations.Fitnesses.*;
import org.example.Algorithms.GA.BusinessLogic.Factories.Interfaces.ChromosomeFactory;
import org.example.Algorithms.GA.BusinessLogic.Factories.Interfaces.CrossOverFactory;
import org.example.Algorithms.GA.BusinessLogic.Factories.Implementations.CrossOvers.MultiCrossOverFactory;
import org.example.Algorithms.GA.BusinessLogic.Factories.Implementations.CrossOvers.SingleCrossOverFactory;
import org.example.Algorithms.GA.BusinessLogic.Factories.Implementations.CrossOvers.UniformCrossOverFactory;
import org.example.Algorithms.GA.BusinessLogic.Factories.Interfaces.FitnessFactory;
import org.example.Algorithms.GA.BusinessLogic.Factories.Interfaces.ISelectionFactory;
import org.example.Algorithms.GA.BusinessLogic.Factories.Implementations.Selections.RouletteWheelSelectionFactory;
import org.example.Algorithms.GA.BusinessLogic.Factories.Implementations.Selections.TournamentSelectionFactory;
import org.example.Algorithms.GA.BusinessLogic.Implementation.Chromosomes.BinaryChromosome;
import org.example.Algorithms.GA.BusinessLogic.Implementation.Chromosomes.FloatingPointChromosome;
import org.example.Algorithms.GA.BusinessLogic.Implementation.Chromosomes.IntegerChromosome;
import org.example.Algorithms.GA.BusinessLogic.Implementation.Chromosomes.Range;
import org.example.Algorithms.GA.BusinessLogic.Contracts.CrossOvers.ICrossOver;
import org.example.Algorithms.GA.BusinessLogic.Implementation.Replacements.ElitistStrategy;
import org.example.Algorithms.GA.BusinessLogic.Implementation.Replacements.GenerationalReplacement;
import org.example.Algorithms.GA.BusinessLogic.Contracts.Replacements.IReplacementStrategy;
import org.example.Algorithms.GA.BusinessLogic.Implementation.Replacements.SteadyStateReplacement;
import org.example.Algorithms.GA.BusinessLogic.Contracts.Selections.ISelection;
import org.example.Algorithms.GA.ApplicationLayer.GAConfig.GAConfig;
import org.example.Algorithms.GA.BusinessLogic.Contracts.Functions.Functions;

import java.util.Map;
import java.util.Scanner;

public class GAUI {
    private final Scanner scanner = new Scanner(System.in);

    public GAConfig collectUserInput() {

        int populationsize = 300;
        double crossoverrate = .7;
        double mutationRate = .02;
        int generations = 10000;
        int numVariables = 4;


        System.out.println("=== Genetic Algorithm Configuration ===");
        System.out.println("Please choose from the following options:\n");
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
        Functions function = fitnessFactory.create();


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
        if (selectionFactory instanceof TournamentSelectionFactory) {
            System.out.print("Choose tournament size: ");
            tournamentSize = scanner.nextInt();
            tournamentSize = Math.max(tournamentSize,1);
            tournamentSize = Math.min(tournamentSize,50);
            selection = selectionFactory.create(tournamentSize);
        } else {
            selection = selectionFactory.create();
        }


        Range[] ranges = new Range[4];
        for (int i = 0; i < 4; i++) {
            System.out.print("Enter range of variable " + (i+1) + " (start end): ");
            double st = scanner.nextDouble();
            double end = scanner.nextDouble();
            if (!Range.checkRange(st, end)) {
                i--;
                System.out.println("Invalid range, try again.");
                continue;
            }
            ranges[i] = new Range(st, end);
        }

        ranges = function.validateInput(ranges);


        System.out.println("\n--- Chromosome Types ---");
        System.out.println("1. Binary Chromosome");
        System.out.println("2. Floating Point Chromosome");
        System.out.println("3. Integer Chromosome");
        System.out.print("Choose chromosome type (1-3): ");
        int chromosomeChoice = scanner.nextInt();

        Map<Integer, ChromosomeFactory> factoryMap = Map.of(
                1, new BinaryChromosome(numVariables, ranges),
                2, new FloatingPointChromosome(numVariables, ranges),
                3, new IntegerChromosome(numVariables, ranges)
        );
        ChromosomeFactory chromosomeFactory = factoryMap.getOrDefault(chromosomeChoice, new BinaryChromosome(numVariables, ranges));


        System.out.println("\n--- Crossover Algorithms ---");
        System.out.println("1. Single Point Crossover");
        System.out.println("2. Multi Point Crossover");
        System.out.println("3. Uniform Crossover");
        System.out.print("Choose crossover (1-3): ");
        int crossoverChoice = scanner.nextInt();

        Map<Integer, CrossOverFactory> crossoverFactoryMap = Map.of(
                1, new SingleCrossOverFactory(),
                2, new MultiCrossOverFactory(),
                3, new UniformCrossOverFactory()
        );

        CrossOverFactory factory = crossoverFactoryMap.getOrDefault(crossoverChoice, new SingleCrossOverFactory());
        ICrossOver crossover;
        if (factory instanceof MultiCrossOverFactory) {
            System.out.print("Enter number of crossover points: ");
            int points = scanner.nextInt();
            crossover = factory.createCrossOver(points);
        } else {
            crossover = factory.createCrossOver();
        }


        System.out.println("\n--- Replacement Strategies ---");
        System.out.println("1. Generational Replacement");
        System.out.println("2. Steady-State Replacement");
        System.out.println("3. Elitist Strategy");
        System.out.print("Choose replacement strategy (1-3): ");
        int replacementChoice = scanner.nextInt();

        Map<Integer, IReplacementStrategy> replacementMap = Map.of(
                1, new GenerationalReplacement(),
                2, new SteadyStateReplacement(),
                3, new ElitistStrategy()
        );
        IReplacementStrategy replacementStrategy = replacementMap.getOrDefault(replacementChoice, new GenerationalReplacement());

        System.out.print("Enter selection size: ");
        int selectionSize = scanner.nextInt();


        GAConfig config = new GAConfig();
        config.populationSize = populationsize;
        config.crossoverRate = crossoverrate;
        config.mutationRate = mutationRate;
        config.generations = generations;
        config.ranges = ranges;
        config.fitnessFunction = function;
        config.selection = selection;
        config.crossover = crossover;
        config.selectionSize = selectionSize;
        config.replacementStrategy = replacementStrategy;
        config.chromosomeFactory = chromosomeFactory;
        config.numVariables = numVariables;

        return config;
    }
}
