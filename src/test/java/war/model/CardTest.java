package war.model;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CardTest {
    @Test
    public void testCompareTo_ReturnsPositiveNumber_WhenRankIsGreater() {
        int result = (new Card(1, 12)).compareTo(new Card(1, 3));

        Assert.assertTrue(result > 0);
    }

    @Test
    public void testCompareTo_ReturnsZero_WhenRankIsEqual() {
        int result = (new Card(1, 12)).compareTo(new Card(1, 12));

        Assert.assertEquals(0, result);
    }

    @Test
    public void testCompareTo_ReturnsNegativeNumber_WhenRankIsLesser() {
        int result = (new Card(1, 1)).compareTo(new Card(1, 3));

        Assert.assertTrue(result < 0);
    }

    @Test
    public void testEquals_ReturnsFalse_WhenOtherIsNull() {
        boolean result = (new Card(1, 12)).equals(null);

        Assert.assertFalse(result);
    }

    @Test
    public void testEquals_ReturnsFalse_WhenOtherIsNotACard() {
        boolean result = (new Card(1, 12)).equals("foo");

        Assert.assertFalse(result);
    }

    @Test
    public void testEquals_ReturnsFalse_WhenOtherHasDifferentRankl() {
        boolean result = (new Card(1, 12)).equals(new Card(1, 11));

        Assert.assertFalse(result);
    }

    @Test
    public void testEquals_ReturnsTrue_WhenOtherHasSameRank_AndSameSuit() {
        boolean result = (new Card(1, 12)).equals(new Card(1, 12));

        Assert.assertTrue(result);
    }

    @Test
    public void testEquals_ReturnsTrue_WhenOtherHasSameRank_AndDifferentSuit() {
        boolean result = (new Card(1, 12)).equals(new Card(2, 12));

        Assert.assertTrue(result);
    }
}