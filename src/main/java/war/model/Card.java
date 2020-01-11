package war.model;

public class Card implements Comparable<Card> {
    private final int suit;
    private final int rank;

    public Card(final int suit, final int rank) {
        this.suit = suit;
        this.rank = rank;
    }

    // todo use lombok instead?
    public int getSuit() {
        return suit;
    }

    public int getRank() {
        return rank;
    }

    @Override
    public int compareTo(Card card) {
        return this.getRank() - card.getRank();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (obj instanceof Card) {
            Card otherCard = (Card) obj;
            return otherCard.getRank() == this.getRank();
        } else {
            return false;
        }
    }
}
