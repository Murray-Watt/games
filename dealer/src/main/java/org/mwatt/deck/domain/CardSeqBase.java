package org.mwatt.deck.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Builder
@Getter
public class CardSeqBase implements CardSeq {
    private List<Card> cards;
    private int topCardIndex;
    @Builder.Default
    private boolean isAceHigh = true;

    public CardSeqBase(boolean isAceHigh) {
        cards = fillDeck(isAceHigh);
        topCardIndex = 0;
        this.isAceHigh = isAceHigh;
    }

    private CardSeqBase(List<Card> cards, int topCardIndex, boolean isAceHigh) {
        this.cards = fillDeck(true);
        this.topCardIndex = topCardIndex;
        this.isAceHigh = isAceHigh;
    }

    static private List<Card> fillDeck(boolean isAceHigh) {
        List<Card> cards = new ArrayList<>();

        for (CardSuit suit : CardSuit.values()) {
            for (CardRank rank : CardRank.values()) {
                cards.add(new Card(rank, suit, isAceHigh));
            }
        }

        return cards;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int add(Card card) {
        List<Card> newCards = new ArrayList<>(this.cards);
        newCards.add(card);
        this.cards = newCards;
        gc();
        return this.cards.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int add(List<Card> cards) {
        List<Card> newCards = new ArrayList<>(this.cards);
        newCards.addAll(cards);
        this.cards = newCards;
        gc();

        return this.cards.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void shuffle() {
        List<Card> shuffledCards = new ArrayList<>(this.cards);
        Collections.shuffle(shuffledCards);
        this.cards = shuffledCards;
        this.topCardIndex = 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean cut(int n) {
        if (n <= 0 || n >= this.cards.size()) {
            return false; // NOOP
        }

        List<Card> newCards = new ArrayList<>();
        List<Card> topCards = cards.subList(0, n);
        List<Card> bottomCards = cards.subList(n, this.cards.size());

        newCards.addAll(bottomCards);
        newCards.addAll(topCards);

        this.cards = newCards;
        this.topCardIndex = 0;
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Card draw() {
        if (topCardIndex >= cards.size()) {
            return null;
        }

        Card drawnCard = cards.get(topCardIndex);
        topCardIndex++;
        return drawnCard;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Card> draw(int n) {
        var cap = Math.min(n, cards.size());
        List<Card> drawnCards = new ArrayList<>(cap);

        for (int i = 0; i < cap; i++) {
            var nextCard = draw();
            if (nextCard != null) {
                drawnCards.add(nextCard);
            }
        }

        return drawnCards;
    }

    // Package level for testing
    void gc() {
        if (this.cards.isEmpty()) {
            return;
        }

        this.cards = new ArrayList<>(this.cards.subList(this.topCardIndex, this.cards.size()));
        this.topCardIndex = 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error while serializing Deck to JSON", e);
        }
    }
}
