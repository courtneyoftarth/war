package war.application;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class WarTest {
    @Test
    public void testWar_EndsInOneBattle_InSimplestGame() {
        War war = new War(1, 2, 2);
        war.battle();

        Assert.assertTrue(war.isOver());
    }

    @Test
    public void testWar_EndsInOneBattle_WhenTheresNotEnoughCards() {
        War war = new War(1, 2, 3);
        war.battle();

        Assert.assertTrue(war.isOver());
    }

    @Test(timeout = 1000)
    public void testWar_EndsEventually_WhenTheresManyRanks() {
        War war = new War(1, 5, 2);
        while (!war.isOver()) {
            System.out.println(war);
            war.battle();
        }
    }

    @Test(timeout = 1000)
    public void testWar_EndsEventually_WhenTheresManyRanksAndSuits() {
        War war = new War(3, 5, 2);
        while (!war.isOver()) {
            war.battle();
        }
    }
}