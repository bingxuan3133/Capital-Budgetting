package cbp;


class FitnessCalculation {
    static int count = 0;

    public static int calcFitness (Individual indie) {
        count++;
        int fitness = (int) Math.round(indie.getTotalReturn() * (double) ProjectTable.UNIT);
        int penalty = calcPenalty(indie);
        return fitness + 2*penalty;
    }

    public static int calcPenalty (Individual indie) {
        int penalty = 0;
        for (int year = 1; year <= ProjectTable.YEARS_OF_INVESTMENT; year++) {
            double budget = ProjectTable.getBudget(year) - indie.getCapitalRequired(year);
            if (budget < 0) {
                penalty += (int) Math.round(budget * (double) ProjectTable.UNIT);
            }
        }
        return penalty;
    }

    private class Constraint {
        static final int MAX_FITNESS_FUNCTION_CALL = 50;
    }

}