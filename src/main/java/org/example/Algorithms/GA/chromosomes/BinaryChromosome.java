package org.example.Algorithms.GA.chromosomes;

import java.util.Random;
import java.util.Arrays;

public class BinaryChromosome extends Chromosome<String> {
    private static final Random rand = new Random();

    private StringBuilder genes;
    private final int numberOfBitsPerVariable;
    private final int numberOfVariables;
    private final Range[] ranges;

    public BinaryChromosome(int numberOfVariables, int numberOfBitsPerVariable, Range[] ranges) {
        this.numberOfVariables = numberOfVariables;
        this.numberOfBitsPerVariable = numberOfBitsPerVariable;
        this.ranges = Arrays.copyOf(ranges, ranges.length);

        int totalBits = numberOfVariables * numberOfBitsPerVariable;
        genes = new StringBuilder(totalBits);

        for (int i = 0; i < totalBits; i++) {
            genes.append(rand.nextInt(2));
        }
    }

    @Override
    public String getGenes() {
        return genes.toString();
    }

    @Override
    public void setGenes(String _genes) {
        this.genes = new StringBuilder(_genes);
    }

    @Override
    public int getGenesNum() {
        return genes.length();
    }

    private int grayToBinary(int gray) {
        int binary = gray;
        while (gray > 0) {
            gray >>= 1;
            binary ^= gray;
        }
        return binary;
    }

    public double[] decode() {
        double[] decoded = new double[numberOfVariables];
        for (int i = 0; i < numberOfVariables; i++) {
            int start = i * numberOfBitsPerVariable;
            int end = start + numberOfBitsPerVariable;
            String grayBits = genes.substring(start, end);
            int grayValue = Integer.parseInt(grayBits, 2);
            int binaryValue = grayToBinary(grayValue);

            double min = ranges[i].getStart();
            double max = ranges[i].getEnd();
            decoded[i] = min + ((double) binaryValue / (Math.pow(2, numberOfBitsPerVariable) - 1)) * (max - min);
        }
        return decoded;
    }

    @Override
    public void mutate(double mutationRate) {
        for (int i = 0; i < genes.length(); i++) {
            if (rand.nextDouble() < mutationRate) {
                genes.setCharAt(i, genes.charAt(i) == '0' ? '1' : '0');
            }
        }
    }

    @Override
    public Chromosome<String> copy() {
        BinaryChromosome copy = new BinaryChromosome(numberOfVariables, numberOfBitsPerVariable, ranges);
        copy.setGenes(this.genes.toString());
        copy.fitness = this.fitness;
        return copy;
    }

    @Override
    public void swapGene(Chromosome<String> c, int index) {
        StringBuilder g1 = new StringBuilder(this.getGenes());
        StringBuilder g2 = new StringBuilder(c.getGenes());
        char temp = g1.charAt(index);

        g1.setCharAt(index, g2.charAt(index));
        g2.setCharAt(index, temp);

        this.setGenes(g1.toString());
        c.setGenes(g2.toString());
    }

    @Override
    public String toString() {
        double[] decoded = decode();
        StringBuilder sb = new StringBuilder();
        sb.append("Fitness=").append(fitness)
                .append(", Genes=").append(genes)
                .append(", Decoded=").append(Arrays.toString(decoded));
        return sb.toString();
    }
}
