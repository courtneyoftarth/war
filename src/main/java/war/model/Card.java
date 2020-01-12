package war.model;

public class Card implements Comparable<Card> {
    private final int suit;
    private final int rank;

    public Card(final int suit, final int rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public int getSuit() {
        return suit;
    }

    public int getRank() {
            return rank;
    }

    @Override
    public int compareTo(Card card) {
        if (card == null) {
            throw new IllegalArgumentException("Card.compareTo does not accept null");
        }
        return this.rank - card.rank;
    }

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

    public String toString() {
        return (char) ('a' + suit) + "" + rank;
    }
}
