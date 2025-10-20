package org.example.Algorithms.GA.crossovers;

import org.example.Algorithms.GA.chromosomes.Chromosome;

import java.util.Random;

public class MultiCrossOver<G> implements ICrossOver<G>{
    int numPoints;
    Random rand;
    public MultiCrossOver(int numPoints){
        if(numPoints < 1){
            throw new IllegalArgumentException("Number of points must be greater than 0");
        }
        this.numPoints = numPoints;
    }
    @Override
    public Chromosome<G>[] apply(Chromosome<G> parent1, Chromosome<G> parent2) {
        int length = parent1.getGenesNum();
        rand = new Random();
        if (numPoints >= length) throw new IllegalArgumentException("numPoints must be less than gene length");

        int[] points = rand.ints(1, length).distinct().limit(numPoints).sorted().toArray();
        Chromosome<G> child1 = parent1.copy();
        Chromosome<G> child2 = parent2.copy();
        boolean swap = false;
        int start = 0;
        for (int point : points) {
            if(swap){
                for(int i = start; i < point; i++){
                    child1.swapGene(child2,i);
                }
            }
            swap=!swap;
            start = point;
        }
        if(swap){
            for(int i = start; i < length; i++){
                child1.swapGene(child2,i);
            }
        }
        return new Chromosome[]{child1, child2};
    }


}
