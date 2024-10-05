package org.mwatt.dealer.api;

import org.mwatt.dealer.domain.CardSeq;
import org.mwatt.dealer.domain.Decks;
import org.mwatt.dealer.domain.StandardCard;
import org.mwatt.dealer.dto.CardDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class DealerController {
    Decks decks = Decks.getInstance();

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/dealer")
    public ResponseEntity<Long> createDeck() {
        // In a real implementation, this would persist the deck and return the ID
        var id = decks.addDeck();
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @PostMapping("/dealer/{id}/draw")
    public ResponseEntity<CardDto> drawCard(@PathVariable Long id) {
        CardSeq<StandardCard> deck = decks.getDeck(id);

        if (deck == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        var card = deck.draw();
        return new ResponseEntity<>(CardDto.toDto(card), HttpStatus.OK);
    }
}
