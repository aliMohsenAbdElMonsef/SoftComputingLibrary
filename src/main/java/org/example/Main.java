package org.example;

import org.example.Algorithms.GA.ApplicationLayer.GAConfig.GAConfig;
import org.example.Algorithms.GA.Presentation.GArunner.GARunner;
import org.example.Algorithms.GA.Presentation.GAui.GAUI;


public class Main {
     static void main(String[] args) {
         GAUI ui = new GAUI();
         GAConfig config = ui.collectUserInput();
         GARunner runner = new GARunner();
         runner.run(config);
    }
}