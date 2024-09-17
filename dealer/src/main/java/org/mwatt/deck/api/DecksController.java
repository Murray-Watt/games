package org.mwatt.deck.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DecksController {
    @GetMapping("/decks")
    public String getDecks() {
        return "Hello";
    }
}
