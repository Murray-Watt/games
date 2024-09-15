package org.mwatt.deck.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {

    @Test
    void buildDeck() {
        Deck deck = Deck.builder().isAceHigh(true).build();

        assertNotNull(deck);
        assertTrue(deck.isAceHigh());
        assertEquals(0, deck.getTopCardIndex());
        assertFalse(deck.getCards().isEmpty());

        ObjectMapper objectMapper = new ObjectMapper();
        String deckJson;

        try {
            deckJson = objectMapper.writeValueAsString(deck);
            assertNotNull(deckJson);
        } catch (JsonProcessingException e) {
            fail("Error while serializing Deck to JSON");
        }
    }
}