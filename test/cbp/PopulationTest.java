package cbp;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class PopulationTest {
    @Before
    public void setUp() {
    }

    @Test
    public void test_toString() {
        Population pop = new Population(5, false);
        assertThat(pop.toString(), containsString("000000"));
    }

    @Test
    public void test_population() {
        Population pop = new Population(5, false);
        pop.replaceIndividual(0, new Individual("100000"));
        pop.replaceIndividual(1, new Individual("111110"));
        pop.replaceIndividual(2, new Individual("100000"));

        assertEquals(pop.getIndividual(1), pop.getFittestIndividual());
    }

    @Test
    public void test_getTotalFitness() {
        Population pop = new Population(5, false);
        pop.replaceIndividual(0, new Individual("100000"));

        assertEquals((int) (1.5*ProjectTable.UNIT), pop.getTotalFitness());
    }


    @Test
    public void test_getBestIndividual() {
        Population pop = new Population(5, false);
        pop.replaceIndividual(0, new Individual("111111"));
        pop.replaceIndividual(1, new Individual("111111"));
        pop.replaceIndividual(2, new Individual("111111"));
        pop.replaceIndividual(3, new Individual("111111"));
        pop.replaceIndividual(4, new Individual("111111"));

        assertEquals(null, pop.getBestIndividual());
    }


}