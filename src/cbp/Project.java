package cbp;

class Project {
    private String name;
    private double expectedReturn;
    private double[] capitalRequired = new double[ProjectTable.YEARS_OF_INVESTMENT];

    Project() {
    }

    Project(String name, double expectedReturn, double... capitalRequired) {
        this.name = name;
        this.expectedReturn = expectedReturn;
        this.capitalRequired = capitalRequired;
    }

    public String getName() {
        return name;
    }

    public double getExpectedReturn() {
        return expectedReturn;
    }

    public double getCapitalRequired(int year) {
        return capitalRequired[(year - 1)];
    }
}

class ProjectTable {
    public static final int UNIT = 1000;
    public static final int NUMBER_OF_PROJECTS = 6;
    public static final int YEARS_OF_INVESTMENT = 3;
    private static final double[] budget = new double[YEARS_OF_INVESTMENT];
    public static final Project projects[] = new Project[NUMBER_OF_PROJECTS];

    static {
        budget[0] = 2.5;
        budget[1] = 2.5;
        budget[2] = 1.5;

        //                    Project Name, ROI,   1,   2,   3
        projects[0] = new Project( "Alpha", 1.5, 0.5, 0.3, 0.2);
        projects[1] = new Project(  "Beta", 3.0, 1.3, 0.8, 0.2);
        projects[2] = new Project( "Gamma", 4.0, 1.5, 1.5, 0.3);
        projects[3] = new Project("Lambda", 0.9, 0.1, 0.4, 0.1);
        projects[4] = new Project( "Sigma", 1.2, 0.3, 0.1, 0.4);
        projects[5] = new Project( "Omega", 0.6, 0.2, 0.1, 0.2);
    }

    public static double getBudget(int year) {
        return ProjectTable.budget[year - 1];
    }
}