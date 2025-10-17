package org.example.Algorithms.GA.chromosomes;

import java.util.Random;

public class BinaryChromosome extends Chromosome {
    private StringBuilder genes;
    private int numberOfBitsPerVariable;
    private int numberOfVariables;
    private Range[] ranges;
    public BinaryChromosome(int numVariables, int numberOfBitsPerVariable, Range[] ranges) {
        this.numberOfBitsPerVariable = numberOfBitsPerVariable;
        this.numberOfVariables = numVariables;
        this.ranges = ranges;
        int totalBits = numVariables * numberOfBitsPerVariable;
        genes = new StringBuilder();
        Random rand = new Random();
        for (int i = 0; i < totalBits; i++) {
            genes.append(rand.nextInt(2));
        }
    }
    @Override
    public Object getGenes() {
        return genes.toString();
    }
    @Override
    public void setGenes(Object genes) {
        String _gene = (String) genes;
        this.genes = new StringBuilder(_gene);
    }
    @Override
    public int getGenesNum() {
        return genes.length();
    }

    @Override
    public Chromosome[] crossover(Chromosome parent2, int point) {
        if (!(parent2 instanceof BinaryChromosome p2))
            throw new IllegalArgumentException("Parent must be BinaryChromosome");

        String g1 = (String) this.getGenes();
        String g2 = (String) p2.getGenes();

        String newG1 = g1.substring(0, point) + g2.substring(point);
        String newG2 = g2.substring(0, point) + g1.substring(point);

        BinaryChromosome child1 = (BinaryChromosome) this.copy();
        BinaryChromosome child2 = (BinaryChromosome) p2.copy();

        child1.setGenes(newG1);
        child2.setGenes(newG2);

        return new Chromosome[]{child1, child2};
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
        Random rand = new Random();
        for (int i = 0; i < genes.length(); i++) {
            if (rand.nextDouble() < mutationRate) {
                char bit = genes.charAt(i);
                genes.setCharAt(i, bit == '0' ? '1' : '0');
            }
        }
    }
    @Override
    public Chromosome copy() {
        BinaryChromosome copy = new BinaryChromosome(numberOfVariables, numberOfBitsPerVariable, ranges);
        copy.genes = new StringBuilder(this.genes);
        copy.fitness = this.fitness;
        return copy;
    }

    @Override
    public String toString() {
        double[] decoded = decode();
        StringBuilder sb = new StringBuilder();
        sb.append("Fitness=").append(fitness)
                .append(", Genes=").append(genes)
                .append(", Decoded=[");

        for (int i = 0; i < decoded.length; i++) {
            sb.append(String.format("%.3f", decoded[i]));
            if (i < decoded.length - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}
