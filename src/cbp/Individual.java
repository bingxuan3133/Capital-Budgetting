package cbp;

import java.util.Random;

public class Individual {

    private int investStatus[] = new int[ProjectTable.projects.length];
    private int fitness = -1;

    public Individual() {}

    public Individual(String bitString) {
        setInvestStatus(bitString);
    }

    public double getTotalReturn() {
        double total = 0;
        for (int i = 0; i < investStatus.length; i++) {
            if (isProjectInvested(i)) {
                total += ProjectTable.projects[i].getExpectedReturn();
            }
        }
        return total;
    }

    public double getCapitalRequired(int year) {
        double total= 0;
        for (int i = 0; i < investStatus.length; i++) {
            if (isProjectInvested(i)) {
                total += ProjectTable.projects[i].getCapitalRequired(year);
            }
        }
        return total;
    }

    private Boolean isProjectInvested(int projectIndex) {
        return investStatus[projectIndex] == 1;
    }

    public void setInvestStatus(String bitString) {
        this.investStatus = atoi(bitString);
    }

    private Boolean isOverBudget(int year) {
        return getCapitalRequired(year) > ProjectTable.getBudget(year);
    }

    // Individual implementation
    public int length() {
        return investStatus.length;
    }

    public int getGene(int index) {
        return investStatus[index];
    }

    public void setGene(int index, int bit) {
        investStatus[index] = bit;
    }

    public void bitFlipGene(int index) {
        investStatus[index] = (investStatus[index] == 1) ? 0 : 1;
    }

    public int getFitness() {
        if (fitness == -1) // which means calculated before
            fitness = FitnessCalculation.calcFitness(this);
        return fitness;
    }

    public Boolean isFeasible() {
        for (int year = 1; year <= ProjectTable.YEARS_OF_INVESTMENT; year++) {
            if(isOverBudget(year)) return false;
        }
        return true;
    }

    public static Individual initialize() {
        Individual individual = new Individual();
        for (int i = 0; i < individual.length(); i++) {
            Random r = new Random();
            individual.setGene(i, r.nextInt(2));
        }
        return individual;
    }

    // Helpers
    @Override
    public String toString() {
        String genes = itoa(investStatus);
        String fitness = Integer.toString(getFitness());
        String feasibility = isFeasible() ? "feasible" : "infeasible";
        return "genes=" + genes + ", " + "fitness=" + fitness + " " + "(" + feasibility + ")";
    }

    private String itoa(int[] genes) {
        String bitString = "";
        for (int i = 0; i < genes.length; i++) {
            bitString += genes[i];
        }
        return bitString;
    }

    private int[] atoi(String bitString) {
        int[] genes = new int[bitString.length()];
        for (int i = 0; i < bitString.length(); i++) {
            genes[i] = (bitString.charAt(i) - '0');
        }
        return genes;
    }
}
