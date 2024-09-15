package org.mwatt.deck.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
public class Deck {
    private List<Card> cards;
    private int topCardIndex;
    @Builder.Default
    private boolean isAceHigh = true;

    public Deck(boolean isAceHigh) {
        this.isAceHigh = isAceHigh;
        cards = createDeck(isAceHigh);
        topCardIndex = 0;
    }

    private Deck(List<Card> cards, int topCardIndex, boolean isAceHigh) {
        this.cards = createDeck(true);
        this.topCardIndex = topCardIndex;
        this.isAceHigh = isAceHigh;
    }

    static private List<Card> createDeck(boolean isAceHigh) {
        List<Card> cards = new ArrayList<>();

        for (CardSuit suit : CardSuit.values()) {
            for (CardRank rank : CardRank.values()) {
                cards.add(new Card(rank, suit, isAceHigh));
            }
        }

        return cards;
    }

    public String toJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error while serializing Deck to JSON", e);
        }
    }
}
