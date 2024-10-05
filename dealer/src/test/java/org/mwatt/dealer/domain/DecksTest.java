package org.mwatt.dealer.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DecksTest {
    private final Decks decks = Decks.getInstance();

    @BeforeEach
    void setUp() {
        decks.clear();
    }

    @Test
    public void addAndGetDeck() {
        Long key = decks.addDeck();
        CardSeq<StandardCard> deck = decks.getDeck(key);
        assertNotNull(deck);
    }

    @Test
    public void removeDeck() {
        Long key1 = decks.addDeck();
        Long key2 = decks.addDeck();
        assertTrue(decks.removeDeck(key1));
        assertFalse(decks.removeDeck(key1)); // Trying to remove the same key again
        assertTrue(decks.removeDeck(key2));
    }

    @Test
    public void getDecks() {
        decks.addDeck();
        decks.addDeck();
         assertEquals(2, decks.getDecks().size());
    }

    @Test
    public void getDeck() {
        Long key1 = decks.addDeck();
        Long key2 = decks.addDeck();

        CardSeq<StandardCard> deck1 = decks.getDeck(key1);
        CardSeq<StandardCard> deck2 = decks.getDeck(key2);
        CardSeq<StandardCard> nonExistentDeck = decks.getDeck(-1L);

        assertNotNull(deck1);
        assertNotNull(deck2);
        assertNull(nonExistentDeck);
    }
}