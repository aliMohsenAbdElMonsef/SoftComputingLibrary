package org.example.Algorithms.NN.Engine;

import org.example.Algorithms.NN.Config.NNConfig;
import org.example.Algorithms.NN.Core.NeuralNetwork;
import org.example.Algorithms.NN.Core.NeuralNetworkBuilder;
import org.example.Algorithms.NN.DataHandling.CSVDataLoader;
import org.example.Algorithms.NN.DataHandling.Dataset;
import org.example.Algorithms.NN.DataHandling.Preproccesing.PreprocessingPipeline;
import org.example.Algorithms.NN.DataHandling.Preproccesing.StandardScaler;
import org.example.Algorithms.NN.Evaluation.Evaluator;
import org.example.Algorithms.NN.InputProviders.InputProvider;
import org.example.Algorithms.NN.Training.TrainingConfig;
import org.example.Algorithms.NN.Training.TrainingEngine;

import java.util.List;

public class NNEngine {
        public void run(InputProvider provider) throws Exception {

                NNConfig config = provider.readConfig();

                Dataset data = CSVDataLoader.loadAndSplit(
                                provider.getDatasetPath(), 0.4, config.seed);

                PreprocessingPipeline pipeline = new PreprocessingPipeline(List.of(new StandardScaler()));
                pipeline.fit(data);

                NeuralNetworkBuilder builder = new NeuralNetworkBuilder()
                                .inputSize(config.inputSize);

                for (int neurons : config.hiddenLayers) {
                        builder.addLayer(
                                        neurons,
                                        config.hiddenActivation(),
                                        config.initializer());
                }

                builder.addLayer(
                                config.outputSize,
                                config.outputActivation(),
                                config.initializer());

                NeuralNetwork network = builder.build();

                TrainingEngine trainer = new TrainingEngine(
                                network,
                                config.lossFunction(),
                                new TrainingConfig(
                                                config.learningRate,
                                                config.epochs,
                                                config.batchSize),
                                config.optimizer);

                trainer.train(data.xTrain, reshape(data.yTrain, config.outputSize));

                Evaluator evaluator = new Evaluator(
                                network,
                                config.lossFunction());

                double testLoss = evaluator.evaluateLoss(
                                data.xTest,
                                reshape(data.yTest, config.outputSize));

                double acc = evaluator.accuracy(
                                data.xTest,
                                reshape(data.yTest, config.outputSize));

                System.out.println("Test Loss: " + testLoss);
                System.out.println("Test Accuracy: " + acc);

        }

        private double[][] reshape(double[] y, int out) {
                double[][] r = new double[y.length][out];
                for (int i = 0; i < y.length; i++)
                        r[i][0] = y[i];
                return r;
        }
}
