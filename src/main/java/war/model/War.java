package war.model;

import java.util.*;
import java.util.stream.Collectors;

public class War {
    private final Map<Integer, Queue<Card>> hands;
    private final int totalCards;

    public War(int numberOfSuits, int numberOfRanks, int numberOfPlayers) {
        Deck deck = new Deck(numberOfSuits, numberOfRanks);
        deck.shuffle();
        totalCards = numberOfSuits * numberOfRanks;

        // Deal
        hands = new HashMap<>();
        for (int i = 0; i < numberOfPlayers; i++) {
            hands.put(i, new LinkedList<>());
        }
        int i = 0;
        while (!deck.isEmpty()) {
            hands.get(i).add(deck.deal());
            i = (i + 1) % numberOfPlayers;
        }
    }

    public boolean isOver() {
        return hands.values().stream().anyMatch(hand -> hand.size() == totalCards);
    }

    public void battle() {
        // Get the best card
        battleHelper(hands);
    }

    private int battleHelper(Map<Integer, Queue<Card>> hands) {
        Map<Integer, Card> playedCards = new HashMap<>();
        for (int i = 0; i < hands.size(); i++) {
            Queue<Card> hand = hands.get(i);
            if (!hand.isEmpty()) {
                Card current = hand.poll();
                playedCards.put(i, current);
            }
        }

        Card bestCard = playedCards.values().stream().max(Comparator.naturalOrder()).get();
        List<Integer> winners = playedCards.keySet().stream().filter(player -> playedCards.get(player).equals(bestCard)).collect(Collectors.toList());

        int winner;
        List<Card> burnedCards = new ArrayList<>();
        if (winners.size() > 1) {
            Map<Integer, Queue<Card>> nextHands = new HashMap<>();
            for (int player : winners) {
                Queue<Card> hand = hands.get(player);
                // burn a card
                if (!hand.isEmpty()) {
                    burnedCards.add(hand.remove());
                }
                // add them to the next round
                nextHands.put(player, hand);
            }
            winner = battleHelper(nextHands);
        } else {
            winner = winners.get(0);
        }
        hands.get(winner).addAll(playedCards.values());
        hands.get(winner).addAll(burnedCards);
        return winner;
    }

    public String toString() {
        String result = "Current hands: \n";
        for (int player : hands.keySet()) {
            result += String.format("\tPlayer %d is holding %s", player, hands.get(player).toString());
        }
        return result;
    }

    public static void play(int numberOfSuits, int numberOfRanks, int numberOfPlayers) {
        War war = new War(numberOfSuits, numberOfRanks, numberOfPlayers);

        while (!war.isOver()) {
            System.out.println(war);
            war.battle();
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Welcome to War!");
        boolean keepPlaying = true;
        while (keepPlaying) {
            System.out.println("Starting a war...");

            System.out.print("Number of suits? ");
            int suits = in.nextInt();
            System.out.print("Number of ranks? ");
            int ranks = in.nextInt();
            System.out.print("Number of players? ");
            int players = in.nextInt();

            play(suits, ranks, players);

            System.out.println("Play again? (y to continue) ");
            keepPlaying = in.next().toLowerCase().startsWith("y");
        }
    }
}
