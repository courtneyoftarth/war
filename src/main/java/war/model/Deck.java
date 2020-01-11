package war.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private final List<Card> cards;

    public Deck(final int numberOfSuits, final int numberOfRanks) {
        cards = new ArrayList<>();
        for (int i = 0; i < numberOfSuits; i++) {
            for (int j = 0; j < numberOfRanks; j++) {
                cards.add(new Card(i, j));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card deal() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("Deck is empty, cannot call deal on an empty deck");
        }
        return cards.remove(0);
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }
}
