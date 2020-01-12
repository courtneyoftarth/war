package war.model;

public class Card implements Comparable<Card> {
    private final int suit;
    private final int rank;

    public Card(final int suit, final int rank) {
        this.suit = suit;
        this.rank = rank;
    }

    @Override
    public int compareTo(Card card) {
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
}
