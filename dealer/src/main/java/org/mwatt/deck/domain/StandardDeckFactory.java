package org.mwatt.deck.domain;

public class StandardDeckFactory {
    static public StandardDeck createDeck(boolean isAceHigh) {
        return StandardDeck.builder().isAceHigh(isAceHigh).build();
    }
}
