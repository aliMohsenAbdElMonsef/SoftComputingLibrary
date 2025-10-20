package org.example;

import org.example.Algorithms.GA.Factories.Fitnesses.*;
import org.example.Algorithms.GA.chromosomes.*;
import org.example.GAConfig.GAConfig;
import org.example.GArunner.GARunner;
import org.example.GAui.GAUI;
import org.example.Algorithms.GA.crossovers.*;


public class Main {
     static void main(String[] args) {
         GAUI ui = new GAUI();
         GAConfig config = ui.collectUserInput();
         GARunner runner = new GARunner();
         runner.run(config);
    }
}