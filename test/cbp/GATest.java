package cbp;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class GATest {

    @Before
    public void setUp() {
    }

    Boolean isValidIndividual(Individual indie) {
        for (int i = 0; i < indie.length(); i++) {
            if(isBinary(indie.getGene(i)))
                continue;
            else return false;
        }
        return true;
    }

    Boolean isBinary(int number) {
        return number == 0 || number == 1;
    }

    @Test
    public void test_onePointCrossover() {
        Individual parent1;
        Individual parent2;
        Individual offspring;

        parent1 = new Individual("110000");
        parent2 = new Individual("000011");

        offspring = GA.onePointCrossover(parent1, parent2, 3);
        assertThat(offspring.toString(), containsString("110011"));
    }

    @Test
    public void test_onePointCrossover_2() {
        Individual parent1;
        Individual parent2;
        Individual offspring;

        parent1 = new Individual("110000");
        parent2 = new Individual("000011");

        offspring = GA.onePointCrossover(parent1, parent2, 6);
        assertThat(offspring.toString(), containsString("110000"));
    }

    @Test
    public void test_onePointCrossover_3() {
        Individual parent1;
        Individual parent2;
        Individual offspring;

        parent1 = new Individual("110000");
        parent2 = new Individual("000011");

        offspring = GA.onePointCrossover(parent1, parent2, 0);
        assertThat(offspring.toString(), containsString("000011"));
    }

    @Test
    public void test_bitFlipMutate() {
        Individual offspring = new Individual("110000");
        int lengthBefore = offspring.length();
        GA.mutationRate = 1;

        offspring = GA.bitFlipMutate(offspring);
        assertEquals(lengthBefore, offspring.length());
        assertEquals(true, this.isValidIndividual(offspring));
        assertThat(offspring.toString(), containsString("001111"));

    }

    @Test
    public void test_bitFlipMutate_2() {
        Individual offspring;

        offspring = new Individual("110000");

        int lengthBefore = offspring.length();
        offspring = GA.bitFlipMutate(offspring);
        assertEquals(lengthBefore, offspring.length());
        assertEquals(true, this.isValidIndividual(offspring));
    }

    @Test
    public void test_tournamentSelect() {
        Population arena = new Population(0, false);
        arena.addIndividual(new Individual("110000"));
        arena.addIndividual(new Individual("111000"));
        arena.addIndividual(new Individual("111100"));

        Individual selected;

        selected = GA.tournamentSelect(arena);
        assertEquals(arena.getIndividual(2), selected);
    }

    @Test
    public void test_evolvePopulation() {
        Population pop = new Population(5, true);
        System.out.print(pop);
        pop = GA.evolvePopulation(pop);
        System.out.print(pop);
    }
}