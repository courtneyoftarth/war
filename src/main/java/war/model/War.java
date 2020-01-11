package war.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class War {
    private final List<List<Card>> hands;
    private final int totalCards;

    public War(int numberOfSuits, int numberOfRanks, int numberOfPlayers) {
        final Deck deck = new Deck(numberOfSuits, numberOfRanks);
        deck.shuffle();
        totalCards = numberOfSuits * numberOfRanks;

        // Deal
        hands = new ArrayList<>();
        for (int i = 0; i < numberOfPlayers; i++) {
            hands.add(new ArrayList<>());
        }
        int i = 0;
        while (!deck.isEmpty()) {
            hands.get(i).add(deck.deal());
            i = (i + 1) % numberOfPlayers;
        }
    }

    public boolean isOver() {
        return hands.stream().anyMatch(hand -> hand.size() == totalCards);
    }

    public void previewBattle() {

    }

    public void battle() {
        // Map from player number to card
        Card bestCard = null;
        for (List<Card> hand : hands) {
            if (!hand.isEmpty()) {
                Card current = hand.get(0);
                if (bestCard == null) {
                    bestCard = current;
                } else {
                    bestCard = bestCard.compareTo(current) > 0 ? bestCard : current;
                }
            }
        }
    }

    public static void play(int numberOfSuits, int numberOfRanks, int numberOfPlayers) {
        War war = new War(numberOfSuits, numberOfRanks, numberOfPlayers);

        while (!war.isOver()) {
            war.battle();
        }
    }
}
