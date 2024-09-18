package org.mwatt.deck.domain;

import lombok.Getter;

import java.util.Objects;

/**
 * StandardCard is the standard playing used in Poker
 * @param rank: Standard values ACE to KING or TWO to ACE depending on isAceHigh
 * @param suit: Standard values of Club to Spade
 * @param isAceHigh: Boolean flag on whether to treat ACE as a low value or high value
 */
public record StandardCard(StandardCard.CardRank rank, StandardCard.CardSuit suit, boolean isAceHigh)
        implements Comparable<StandardCard> {

    /*
          This is a package level class and thus it has no JavaDoc

          The only significant feature of the class is that ACE can be low or high
     */
    @Getter
    public enum CardRank {
        ACE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(11), QUEEN(12), KING(13);

        private final int value;

        CardRank(int value) {
            this.value = value;
        }

        // Implement a custom compareTo method with isAceHigh flag
        public int compareTo(CardRank otherRank, boolean isAceHigh) {
            var aceFactor = isAceHigh && (this == CardRank.ACE || otherRank == CardRank.ACE);

            // Minor optimization from Charter discussion.
            return Integer.compare(this.value, otherRank.getValue()) * (aceFactor ? -1 : 1);
        }
    }

    /*
      This is a package level class and simple enough that it does not need commenting
    */
    @Getter
    public enum CardSuit {
        CLUBS(1),
        DIAMONDS(2),
        HEARTS(3),
        SPADES(4);

        private final int suitRank;

        CardSuit(int suitRank) {
            this.suitRank = suitRank;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof StandardCard otherCard) {
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
    public int compareTo(StandardCard otherCard) {
        int rankComparison = rank().compareTo(otherCard.rank(),isAceHigh);
        if (rankComparison != 0) {
            return rankComparison;
        } else {
            return suit().compareTo(otherCard.suit());
        }
    }
}
