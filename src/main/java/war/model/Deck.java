package war.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private final List<Card> cards;

    /**
     * Creates a Deck with the given number of suits and ranks.
     * Deck is initially unshuffled.
     * @param numberOfSuits Number of suits to include
     * @param numberOfRanks Number of ranks to include
     */
    public Deck(int numberOfSuits, int numberOfRanks) {
        cards = new ArrayList<>();
        for (int i = 0; i < numberOfSuits; i++) {
            for (int j = 0; j < numberOfRanks; j++) {
                cards.add(new Card(i, j));
            }
        }
    }

    /**
     * Shuffles the deck
     */
    public void shuffle() {
        Collections.shuffle(cards);
    }

    /**
     * Removes a card from the deck and deals it
     * @return A card removed from the deck
     * @throws IllegalStateException if there are no cards left in the deck
     */
    public Card deal() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("Deck is empty, cannot call deal on an empty deck");
        }
        return cards.remove(0);
    }

    /**
     * Checks whether the deck is empty
     * @return True if the deck is empty, false otherwise
     */
    public boolean isEmpty() {
        return cards.isEmpty();
    }
}
