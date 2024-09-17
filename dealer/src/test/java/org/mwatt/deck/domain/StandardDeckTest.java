package org.mwatt.deck.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StandardDeckTest {

    @Test
    public void buildDeck() {
        StandardDeck deck = StandardDeckFactory.createDeck(true);

        assertNotNull(deck);
        assertTrue(deck.isAceHigh());
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
    public void drawHappyPath() {
        StandardDeck deck = StandardDeckFactory.createDeck(true);
        assertNotNull(deck);

        StandardCard drawnCard = deck.draw();
        assertNotNull(drawnCard);
    }

    @Test
    public void drawEmptyDeck() {
        StandardDeck deck = StandardDeckFactory.createDeck(true);
        assertNotNull(deck);

        while (!deck.getCards().isEmpty()) {
            deck.draw();
        }

        assertNull(deck.draw());
    }

    @Test
    public void drawMultipleCards() {
        StandardDeck deck = StandardDeckFactory.createDeck(true);

        List<StandardCard> drawnCards = deck.draw(3);
        assertEquals(3, drawnCards.size());
    }

    @Test
    public void shuffle() {
        StandardDeck deck = StandardDeckFactory.createDeck(true);

        deck.shuffle();

        assertEquals(52, deck.getCards().size());
    }

    @Test
    public void drawMoreCardsThanAvailable() {
        StandardDeck deck = StandardDeckFactory.createDeck(true);

        List<StandardCard> drawnCards = deck.draw(54); // 52 cards in a standard deck

        assertEquals(52, drawnCards.size());
    }

    // https://stattrek.com/online-calculator/binomial
    @Test
    public void shuffleChangesCards() {
        StandardDeck deck = StandardDeckFactory.createDeck(true);

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
        StandardDeck deck = StandardDeckFactory.createDeck(true);
        var topCard = deck.getCards().getFirst();

        deck.cut(3);

        assertEquals(deck.getCards().get(deck.getCards().size() - 3), topCard);
    }

    @Test
    public void addOneCardToBottom() {
        StandardDeck deck = StandardDeckFactory.createDeck(true);

        StandardCard drawnCard = deck.draw();
        deck.add(drawnCard);
        assertEquals(drawnCard, deck.getCards().getLast());
    }

    @Test
    public void addMultipleCardsToBottom() {
        StandardDeck deck = StandardDeckFactory.createDeck(true);

        List<StandardCard> cardsToAdd = new ArrayList<>();
        cardsToAdd.add(deck.draw());
        cardsToAdd.add(deck.draw());
        cardsToAdd.add(deck.draw());

        deck.add(cardsToAdd);

        assertEquals(cardsToAdd.get(0), deck.getCards().get(deck.getCards().size() - 3));
        assertEquals(cardsToAdd.get(1), deck.getCards().get(deck.getCards().size() - 2));
        assertEquals(cardsToAdd.get(2), deck.getCards().getLast());
    }

    @Test
    public void gc() {
        StandardDeck deck = StandardDeckFactory.createDeck(true);

        deck.draw();
        deck.draw();
        deck.draw();

        assertEquals(49, deck.getCards().size());
    }
}