package cbp;

import java.util.concurrent.ThreadLocalRandom;

class Random {
    // lowerBound is inclusive
    // upperBound is exclusive
    static int randomRange(int lowerBound, int upperBound) {
        return ThreadLocalRandom.current().nextInt(lowerBound, upperBound);
    }
}

class Main {
    public static void main(String[] args) {
        GA.runGA();
    }
}

class GA {
    private static double uniformCrossoverRate = 0.5;
    public static double mutationRate = 0.2;
    static final int tournamentSize = 3;
    static Selection parentSelection = GA::tournamentSelect;
    static Selection survivorSelection = GA::tournamentSelect;
    static Recombination recombinationOperator = GA::oneRandomPointCrossover;
    static Mutation mutationOperator = GA::bitFlipMutate;
    static int elitistCount = 1;

    public static void runGA() {
        int totalFitness = 0;

        Population pop = new Population(10, true);

        while (FitnessCalculation.count < 50) {
            totalFitness = pop.getTotalFitness();
            System.out.println("avgFitness:" + totalFitness/pop.getSize());
            System.out.println(pop);
            System.out.println("=====================");

            pop = evolvePopulation(pop);
            System.out.println("FitnessCalculation.count:" + FitnessCalculation.count);
        }
    }

    static Population evolvePopulation(Population pop) {
        Population newGen = new Population(0, false);

        for (int i = 0; i > elitistCount; i++) {
            newGen.addIndividual(elitism(pop));
        }

        for (int i = 0; i < (pop.getSize() - elitistCount); i++) {
            Individual papa = parentSelection.run(pop);
            Individual mama = parentSelection.run(pop);
            Individual baby = recombinationOperator.run(papa, mama);
            baby = mutationOperator.run(baby);

            newGen.addIndividual(baby);
        }
        return newGen;
    }

    static Individual elitism(Population oldGen) {
        Population clone = oldGen.clone();
        return clone.getFittestIndividual();
    }

    @FunctionalInterface
    interface Recombination {
        Individual run(Individual indie1, Individual indie2);
    }

    static Individual oneRandomPointCrossover(Individual indie1, Individual indie2) {
        int crossoverPoint = ThreadLocalRandom.current().nextInt(0, indie1.length() + 1);
        return onePointCrossover(indie1, indie2, crossoverPoint);
    }

    static Individual onePointCrossover(Individual indie1, Individual indie2, int index) {
        Individual offspring = new Individual();

        for (int i = 0; i < indie1.length(); i++) {
            if (i < index) {
                offspring.setGene(i, indie1.getGene(i));
            } else {
                offspring.setGene(i, indie2.getGene(i));
            }
        }
        return offspring;
    }

    @FunctionalInterface
    interface Mutation {
        Individual run(Individual indie);
    }

    static Individual bitFlipMutate(Individual indie) {
        for (int i = 0; i < indie.length(); i++) {
            if (Math.random() < mutationRate) {
                indie.bitFlipGene(i);
            }
        }
        return indie;
    }

    @FunctionalInterface
    interface Selection {
        Individual run(Population pop);
    }

    static Individual tournamentSelect(Population pop) {
        Population clone = pop.clone();
        Population arena = new Population(0, false);

        for (int i = 0; i < tournamentSize; i++) {
            int randomIndex = ThreadLocalRandom.current().nextInt(0, clone.getSize());
            arena.addIndividual(clone.popIndividual(randomIndex));
        }
        return arena.getFittestIndividual();
    }



}