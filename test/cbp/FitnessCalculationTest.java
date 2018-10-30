package cbp;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FitnessCalculationTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void test_calcPenalty_overbudget() {
        Individual individual = new Individual("011000");
        FitnessCalculation.calcPenalty(individual);
        assertEquals((int) (-0.3*ProjectTable.UNIT), FitnessCalculation.calcPenalty(individual));
    }

    @Test
    public void test_calcPenalty_just_right_budget() {
        Individual individual = new Individual("101011");
        FitnessCalculation.calcPenalty(individual);
        assertEquals(0, FitnessCalculation.calcPenalty(individual));
    }

    @Test
    public void test_calcPenalty_within_budget() {
        Individual individual = new Individual("010000");
        FitnessCalculation.calcPenalty(individual);
        assertEquals(0, FitnessCalculation.calcPenalty(individual));
    }
}