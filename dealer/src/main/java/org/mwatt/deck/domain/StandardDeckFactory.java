package org.mwatt.deck.domain;

public class StandardDeckFactory {
    static public StandardDeck createDeck(boolean isAceHigh) {
        StandardDeck deck = StandardDeck.builder().isAceHigh(isAceHigh).build();
        deck.initializeDeck(isAceHigh);

        return deck;
    }
}
