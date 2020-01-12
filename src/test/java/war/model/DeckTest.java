package war.model;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DeckTest {
    @Test
    public void testIsEmpty_ReturnsTrue_WhenDeckIsEmpty() {
        boolean result = (new Deck(0, 0)).isEmpty();

        Assert.assertTrue(result);
    }

    @Test
    public void testIsEmpty_ReturnsFalse_WhenDeckIsNotEmpty() {
        boolean result = (new Deck(1, 1)).isEmpty();

        Assert.assertFalse(result);
    }

    @Test
    public void testIsEmpty_ReturnsTrue_WhenDeckIsDealt() {
        Deck deck = new Deck(1, 1);
        deck.deal();
        Assert.assertTrue(deck.isEmpty());
    }

    @Test
    public void testDeal_ReturnsCorrectNumberOfCards() {
        Deck deck = new Deck(2, 3);

        int count = 0;
        while (!deck.isEmpty()) {
            count++;
            deck.deal();
        }
        Assert.assertEquals(6, count);
    }

    @Test
    public void testDeal_ReturnsCorrectCards() {
        int suits = 3, ranks = 4;
        Deck deck = new Deck(suits, ranks);

        boolean[][] seenCards = new boolean[suits][ranks];
        while (!deck.isEmpty()) {
            Card card = deck.deal();
            seenCards[card.getSuit()][card.getRank()] = true;
        }

        for (int i = 0; i < suits; i++) {
            for (int j = 0; j < ranks; j++) {
                Assert.assertTrue(String.format("Expected deck to contain card with suit %d and rank %d", i, j), seenCards[i][j]);
            }
        }
    }

    @Test(expected = IllegalStateException.class)
    public void testDeal_ThrowsException_WhenDeckIsEmpty() {
        (new Deck(0, 0)).deal();
    }

    @Test
    public void testShuffle_PreservesCards() {
        int suits = 3, ranks = 4;
        Deck deck = new Deck(suits, ranks);
        deck.shuffle();

        boolean[][] seenCards = new boolean[suits][ranks];
        while (!deck.isEmpty()) {
            Card card = deck.deal();
            seenCards[card.getSuit()][card.getRank()] = true;
        }

        for (int i = 0; i < suits; i++) {
            for (int j = 0; j < ranks; j++) {
                Assert.assertTrue(String.format("Expected deck to contain card with suit %d and rank %d", i, j), seenCards[i][j]);
            }
        }
    }
}