package org.mwatt.deck.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CardSeqBaseTest {

    @Test
    public void buildDeck() {
        CardSeqBase deck = CardSeqBase.builder().isAceHigh(true).build();

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

    @Test
    public void toJsonOnInitialDeck() {
        CardSeqBase deck = CardSeqBase.builder().isAceHigh(true).build();
        String deckJson;

        try {
            deckJson = deck.toJson();
            assertNotNull(deckJson);
            assertFalse(deckJson.isEmpty());
            assertTrue(deckJson.contains("\"isAceHigh\":true"));
            assertTrue(deckJson.contains("\"topCardIndex\":0"));
            assertTrue(deckJson.contains("\"cards\":["));
        } catch (RuntimeException e) {
            fail("Error while serializing Deck to JSON");
        }
    }

    @Test
    public void drawHappyPath() {
        CardSeqBase deck = CardSeqBase.builder().isAceHigh(true).build();
        assertNotNull(deck);

        assertEquals(0, deck.getTopCardIndex());

        StandardCard drawnCard = deck.draw();
        assertNotNull(drawnCard);

        assertEquals(1, deck.getTopCardIndex());
    }

    @Test
    public void drawEmptyDeck() {
        CardSeqBase deck = CardSeqBase.builder().isAceHigh(true).build();
        assertNotNull(deck);

        while (deck.getTopCardIndex() < deck.getCards().size()) {
            deck.draw();
        }

        assertNull(deck.draw());
    }

    @Test
    public void drawMultipleCards() {
        CardSeqBase deck = CardSeqBase.builder().isAceHigh(true).build();

        List<StandardCard> drawnCards = deck.draw(3);
        assertEquals(3, drawnCards.size());

        assertEquals(3, deck.getTopCardIndex());
    }

    @Test
    public void shuffle() {
        CardSeqBase deck = CardSeqBase.builder().isAceHigh(true).build();

        deck.shuffle();

        assertEquals(52, deck.getCards().size());
        assertEquals(0, deck.getTopCardIndex());
    }

    @Test
    public void drawMoreCardsThanAvailable() {
        CardSeqBase deck = CardSeqBase.builder().isAceHigh(true).build();

        List<StandardCard> drawnCards = deck.draw(54); // 52 cards in a standard deck

        assertEquals(52, drawnCards.size());
        assertEquals(52, deck.getTopCardIndex());
    }

    // https://stattrek.com/online-calculator/binomial
    @Test
    public void shuffleChangesCards() {
        CardSeqBase deck = CardSeqBase.builder().isAceHigh(true).build();

        List<StandardCard> topCardsBeforeShuffle = deck.getCards().subList(0, 9);
        deck.shuffle();

        List<StandardCard> topCardsAfterShuffle = deck.getCards().subList(0, 9);

        int movedCardsCount = 0;
        for (int i = 0; i < 9; i++) {
            if (!topCardsBeforeShuffle.get(i).equals(topCardsAfterShuffle.get(i))) {
                movedCardsCount++;
            }
        }

        assertTrue(movedCardsCount >= 5);
    }

    @Test
    public void cut() {
        CardSeqBase deck = CardSeqBase.builder().isAceHigh(true).build();
        var topCard = deck.getCards().getFirst();

        deck.cut(3);

        assertEquals(deck.getCards().get(deck.getCards().size() - 3), topCard);
        assertEquals(0, deck.getTopCardIndex());
    }

    @Test
    public void addOneCardToBottom() {
        CardSeqBase deck = CardSeqBase.builder().isAceHigh(true).build();

        StandardCard drawnCard = deck.draw();
        deck.add(drawnCard);
        assertEquals(drawnCard, deck.getCards().getLast());

        assertEquals(0, deck.getTopCardIndex());
    }

    @Test
    public void addMultipleCardsToBottom() {
        CardSeqBase deck = CardSeqBase.builder().isAceHigh(true).build();

        List<StandardCard> cardsToAdd = new ArrayList<>();
        cardsToAdd.add(deck.draw());
        cardsToAdd.add(deck.draw());
        cardsToAdd.add(deck.draw());

        deck.add(cardsToAdd);

        assertEquals(cardsToAdd.get(0), deck.getCards().get(deck.getCards().size() - 3));
        assertEquals(cardsToAdd.get(1), deck.getCards().get(deck.getCards().size() - 2));
        assertEquals(cardsToAdd.get(2), deck.getCards().getLast());

        assertEquals(0, deck.getTopCardIndex());
    }

    @Test
    public void gc() {
        CardSeqBase deck = CardSeqBase.builder().isAceHigh(true).build();

        deck.draw();
        deck.draw();
        deck.draw();

        deck.gc();

        assertEquals(49, deck.getCards().size());
        assertEquals(0, deck.getTopCardIndex());
    }
}