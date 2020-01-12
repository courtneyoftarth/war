package war.model;

public class Card implements Comparable<Card> {
    private final int suit;
    private final int rank;

    /**
     * Creates a card with the given suit and rank
     * @param suit A number representing the suit
     * @param rank A number representing the rank
     */
    public Card(final int suit, final int rank) {
        this.suit = suit;
        this.rank = rank;
    }

    /**
     * @return The suit of the card
     */
    public int getSuit() {
        return suit;
    }

    /**
     * @return The rank of the card
     */
    public int getRank() {
            return rank;
    }

    /**
     * Compares this card to another. Cards are compared by rank. The suit does not matter.
     * @param card The card to compare to
     * @return A positive number if this card is greater, 0 if this card is equal, and a negative number if this card is lesser.
     * @throws IllegalArgumentException if the other card is null
     */
    @Override
    public int compareTo(Card card) {
        if (card == null) {
            throw new IllegalArgumentException("Card.compareTo does not accept null");
        }
        return this.rank - card.rank;
    }

    /**
     * Checks if this card is equal to the other object.
     * Two cards are equal if they have the same rank. Suit does not matter.
     * @param obj The object to compare to.
     * @return True if the other object is a Card with the same rank, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (obj instanceof Card) {
            Card otherCard = (Card) obj;
            return otherCard.rank == this.rank;
        } else {
            return false;
        }
    }

    /**
     * @return A string representation of the card's suit and rank
     */
    public String toString() {
        return (char) ('a' + suit) + "" + rank;
    }
}
