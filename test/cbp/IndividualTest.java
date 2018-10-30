package cbp;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

public class IndividualTest {
    Individual individual;

    @Before
    public void setUp() {
        individual = new Individual();
    }

    @Test
    public void test_toString() {
        individual.setGene(2, 1);
        individual.setGene(3, 1);
        assertThat(individual.toString(), containsString("001100"));
        assertEquals(0, individual.getGene(0));
        assertEquals(0, individual.getGene(1));
        assertEquals(1, individual.getGene(2));

    }

    @Test
    public void test_getTotalReturn() {
        assertEquals(0, individual.getTotalReturn(), 0.001);
        individual.setGene(0, 1);
        assertEquals(1.5, individual.getTotalReturn(), 0.001);
        individual.setGene(1, 1);
        assertEquals(4.5, individual.getTotalReturn(), 0.001);
    }

    @Test
    public void test_getTotalReturn_1() {
        assertEquals(0, individual.getTotalReturn(), 0.001);
        individual.setInvestStatus("100000");
        assertEquals(1.5, individual.getTotalReturn(), 0.001);
        individual.setInvestStatus("000011");
        assertEquals(1.8, individual.getTotalReturn(), 0.001);
    }

    @Test
    public void test_getCapitalRequired() {
        assertEquals(0, individual.getCapitalRequired(1), 0.001);
        individual.setGene(0, 1);
        assertEquals(0.5, individual.getCapitalRequired(1), 0.001);
    }

    @Test
    public void test_getCapitalRequired_1() {
        assertEquals(0, individual.getCapitalRequired(3), 0.001);
        individual.setInvestStatus("100000");
        assertEquals(0.2, individual.getCapitalRequired(3), 0.001);
        individual.setInvestStatus("000011");
        assertEquals(0.6, individual.getCapitalRequired(3), 0.001);
    }

    @Test
    public void test_isFeasible_with_arg_year() {
        assertEquals(true, individual.isFeasible());
        individual.setInvestStatus("100000");
        assertEquals(true, individual.isFeasible());
        individual.setInvestStatus("111111");
        assertEquals(false, individual.isFeasible());
    }



}