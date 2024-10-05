package org.mwatt.dealer.api;

import org.junit.jupiter.api.Test;
import org.mwatt.dealer.dto.CardDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class DealerControllerTest {

    private final DealerController dealerController = new DealerController();

    @Test
    public void createDeckHappyPath() {
        ResponseEntity<Long> response = dealerController.createDeck();

        assertNotNull(response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void drawCardHappyPath() {
        ResponseEntity<Long> response = dealerController.createDeck();

        var deckId = response.getBody();

        // TODO: In progress
        ResponseEntity<CardDto> drawResponse = dealerController.drawCard(deckId);

        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}