package org.mwatt.deck.domain;

import lombok.Getter;

/**
 * Enum representing card suits with standard ordering for most card games.
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
