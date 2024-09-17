package org.mwatt.deck.domain;

import lombok.Getter;

@Getter
public enum CardRank {
    ACE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(11), QUEEN(12), KING(13);

    private final int value;

    CardRank(int value) {
        this.value = value;
    }

    // Implement a custom compareTo method with isAceHigh flag
    public int compareTo(CardRank otherRank, boolean isAceHigh) {

        // Reverse comparison when this is ACE High
        if (isAceHigh && (this == CardRank.ACE || otherRank == CardRank.ACE)) {
            return Integer.compare(otherRank.getValue(),this.value);
        }

        return Integer.compare(this.value, otherRank.getValue());
    }
}
