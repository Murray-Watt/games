package org.mwatt.deck.api;

import org.mwatt.deck.domain.CardSeq;
import org.mwatt.deck.domain.Decks;
import org.mwatt.deck.domain.StandardCard;
import org.mwatt.deck.domain.StandardDeck;
import org.mwatt.deck.dto.CardDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.smartcardio.Card;

@RestController
public class DeckController {
    Decks decks = Decks.getInstance();

    @GetMapping("/deck")
    public ResponseEntity<Long> createDeck() {
        // In a real implementation, this would persist the deck and return the ID
        var id = decks.addDeck();
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public String adminDeck() {
        return "hello Admin";
    }


    @PostMapping("/deck/{id}/draw")
    public ResponseEntity<CardDto> drawCard(@PathVariable Long id) {
        CardSeq<StandardCard> deck = decks.getDeck(id);

        if (deck == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        var card = deck.draw();
        return new ResponseEntity<>(CardDto.toDto(card), HttpStatus.OK);
    }
}
