package org.mwatt.deck.domain;

import java.util.Objects;

public record Card(CardRank rank, CardSuit suit, boolean isAceHigh) implements Comparable<Card> {
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Card otherCard) {
            return this.rank() == otherCard.rank() && this.suit() == otherCard.suit();
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank(), suit());
    }

    @SuppressWarnings("preview")
    @Override
    public String toString() {
        return STR." \{rank().getValue()} of \{suit()}";
    }

    @Override
    public int compareTo(Card otherCard) {
        int rankComparison = rank().compareTo(otherCard.rank(),isAceHigh);
        if (rankComparison != 0) {
            return rankComparison;
        } else {
            return suit().compareTo(otherCard.suit());
        }
    }
}
