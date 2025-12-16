package org.example.Algorithms.NN.WeightInitialization;

public class InitializerFactory {
    public static WeightInitializer randomUniform(long seed) {
        return new RandomUniformInitializer(-0.5, 0.5, seed);
    }

    public static WeightInitializer xavier(long seed) {
        return new Xavier(seed);
    }

    public static WeightInitializer he(long seed) {
        return new He(seed);
    }
}
