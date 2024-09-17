package org.mwatt.deck.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mwatt.deck.dto.CardDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class DeckControllerTest {

    private final DeckController deckController = new DeckController();;

    @Test
    public void createDeckHappyPath() {
        ResponseEntity<Long> response = deckController.createDeck();

        assertNotNull(response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void drawCardHappyPath() {
        ResponseEntity<Long> response = deckController.createDeck();

        var deckId = response.getBody();
        ResponseEntity<CardDto> drawResponse = deckController.drawCard(deckId);

        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}