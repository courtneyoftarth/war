package war.application;

import war.model.Card;
import war.model.Deck;

import java.util.*;
import java.util.stream.Collectors;

public class War {
    private final Map<Integer, Queue<Card>> hands;
    private final int totalCards;

    /**
     * Initializes a game of War
     * @param numberOfSuits Number of suits to use
     * @param numberOfRanks Number of ranks to use
     * @param numberOfPlayers Number of players
     */
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

    /**
     * Checks if the game is over. The game ends when one person has all the cards.
     * @return True if the game is over, false otherwise.
     */
    public boolean isOver() {
        return hands.values().stream().anyMatch(hand -> hand.size() == totalCards);
    }

    /**
     * Plays a single battle.
     * A battle is when everyone flips over a card and the person with the highest card takes all the flipped cards.
     * When there is a tie, everyone who tied burns a card and flips another to compare.
     * Whoever ultimately wins the tie takes all the played and burned cards.
     */
    public void battle() {
        // Get the best card
        battleHelper(hands);
    }

    /**
     * Plays a battle with the given hands.
     * @param hands Map from player id to that player's hand of cards
     * @return The winning player id
     */
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

    /**
     * @return A string representation of the game's current state
     */
    public String toString() {
        String result = "Current hands: \n";
        for (int player : hands.keySet()) {
            result += String.format("\tPlayer %d is holding %s\n", player, hands.get(player).toString());
        }
        return result;
    }

    /**
     * Plays an entire game of war.
     * @param numberOfSuits Number of suits
     * @param numberOfRanks Number of ranks
     * @param numberOfPlayers Number of players
     */
    public static void play(int numberOfSuits, int numberOfRanks, int numberOfPlayers) {
        War war = new War(numberOfSuits, numberOfRanks, numberOfPlayers);

        while (!war.isOver()) {
            System.out.println(war);
            war.battle();
        }
    }

    /**
     * Play War in the command line.
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Welcome to War!");
        boolean keepPlaying = true;
        while (keepPlaying) {
            System.out.println("Starting a war...");

            System.out.println("Number of suits? ");
            int suits = in.nextInt();
            System.out.println("Number of ranks? ");
            int ranks = in.nextInt();
            System.out.println("Number of players? ");
            int players = in.nextInt();

            play(suits, ranks, players);

            System.out.println("Play again? (y to continue) ");
            keepPlaying = in.next().toLowerCase().startsWith("y");
        }
        System.out.println("Goodbye!");
    }
}
