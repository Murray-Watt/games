package org.mwatt.deck.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DecksTest {
    private Decks decks = Decks.getInstance();

    @BeforeEach
    void setUp() {
        decks.clear();
    }

    @Test
    public void addAndGetDeck() {
        int key = decks.addDeck();
        Deck deck = decks.getDeck(key);
        assertNotNull(deck);
    }

    @Test
    public void removeDeck() {
        int key1 = decks.addDeck();
        int key2 = decks.addDeck();
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
        int key1 = decks.addDeck();
        int key2 = decks.addDeck();

        Deck deck1 = decks.getDeck(key1);
        Deck deck2 = decks.getDeck(key2);
        Deck nonExistentDeck = decks.getDeck(-1);

        assertNotNull(deck1);
        assertNotNull(deck2);
        assertNull(nonExistentDeck);
    }
}