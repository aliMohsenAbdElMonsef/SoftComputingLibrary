package org.example.Algorithms.GA.BusinessLogic.Implementation.Chromosomes;

import org.example.Algorithms.GA.BusinessLogic.Contracts.Chromosomes.Chromosome;
import org.example.Algorithms.GA.BusinessLogic.Factories.Interfaces.ChromosomeFactory;

import java.util.Random;
import java.util.Arrays;

public class BinaryChromosome extends Chromosome<String> implements ChromosomeFactory<String> {
    private static final Random rand = new Random();

    private StringBuilder genes;
    private final int numberOfBitsPerVariable = 10;
    private final int numberOfVariables;
    private final Range[] ranges;

    public BinaryChromosome(int ChromosomeLength, Range[] ranges) {
        super(ChromosomeLength, ranges);
        this.numberOfVariables = ChromosomeLength;
        this.ranges = checkRanges(Arrays.copyOf(ranges, ranges.length));
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
    @Override
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
        BinaryChromosome copy = new BinaryChromosome(numberOfVariables, ranges);
        copy.setGenes(this.genes.toString());
        copy.setFitness(this.getFitness());
        return copy;
    }
    private Range[] checkRanges(Range[] ranges) {
        if (ranges == null || ranges.length == 0) {
            throw new IllegalArgumentException("ranges array cannot be null or empty");
        }
        Range[] out = new Range[ranges.length];
        for (int i = 0; i < ranges.length; i++) {
            Range r = ranges[i];
            if (r == null) {
                throw new IllegalArgumentException("range at index " + i + " is null");
            }
            double start = r.getStart();
            double end = r.getEnd();
            if (start > end) {
                throw new IllegalArgumentException("Invalid range at index " + i + ": start > end");
            }
            if ((end - start) > 1024.0) {
                out[i] = new Range(start, start+1024.0);
                continue;
            }
            out[i] = new Range(start, end);
        }

        return out;
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

    @Override
    public Chromosome<String> create(int ChromosomeLength, Range[] ranges) {
        return new BinaryChromosome(ChromosomeLength, ranges);
    }

    @Override
    public String getChromosomeType() {
        return "Binary";
    }
}
