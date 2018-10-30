package cbp;

import java.util.ArrayList;

class Population {

    private ArrayList<Individual> individuals;

    Population(int size, Boolean randomInit) {
        individuals = new ArrayList<Individual>();
        // Initialise population
        if (randomInit) {
            // Loop and create individuals
            for (int i = 0; i < size; i++) {
                Individual newIndividual = Individual.initialize();
                addIndividual(newIndividual);
            }
        } else {
            for (int i = 0; i < size; i++) {
                addIndividual(new Individual());
            }
        }
    }

    public int getSize() {
        return individuals.size();
    }

    @Override
    public Population clone() {
        Population cloned = new Population(getSize(), false);
        cloned.individuals = (ArrayList<Individual>) individuals.clone();
        return cloned;
    }

    public int getTotalFitness() {
        int fitness = 0;
        for (Individual indie:individuals) {
            fitness += indie.getFitness();
        }
        return fitness;
    }

    public Individual getIndividual(int index) {
        return individuals.get(index);
    }

    public Individual popIndividual(int index) {
        return individuals.remove(index);
    }

    public Boolean removeIndividual(Individual indie) {
        return individuals.remove(indie);
    }

    public void replaceIndividual(int index, Individual individual) {
        individuals.set(index, individual);
    }

    public void addIndividual(Individual individual) {
        individuals.add(individual);
    }

    public Individual getFittestIndividual() {
        Individual fittest = null;
        for (Individual indie:individuals) {
            if (fittest == null) {
                fittest = indie;
            }
            else if (fittest.getFitness() < indie.getFitness()) {
                fittest = indie;
            }
        }
        return fittest;
    }

    public Individual getBestIndividual() {
        Population clone = this.clone();
        while(clone.getSize() > 0) {
            Individual fittest = clone.getFittestIndividual();
            if(fittest.isFeasible()) { // found fittest & feasible
                return fittest;
            }
            clone.removeIndividual(fittest); // remove fittest to find next fittest
        }
        return null;
    }

    // Helpers
    @Override
    public String toString() {
        String s = "";
        for(Individual individual:individuals) {
            s += individual.toString() + "\n";
        }
        return s;
    }

}