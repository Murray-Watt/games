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

    @Getter
    public enum CardRank {
        ACE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(11), QUEEN(12), KING(13);

        private final int value;

        CardRank(int value) {
            this.value = value;
        }

        // Implement a custom compareTo method with isAceHigh flag
        public int compareTo(CardRank otherRank, boolean isAceHigh) {

            // Reverse comparison when this is ACE High
            if (isAceHigh && (this == CardRank.ACE || otherRank == CardRank.ACE)) {
                return Integer.compare(otherRank.getValue(),this.value);
            }

            return Integer.compare(this.value, otherRank.getValue());
        }
    }

    @Builder.Default
    private List<StandardCard> cards = fillDeck(true);
    @Builder.Default
    private int topCardIndex = 0;
    @Builder.Default
    private boolean isAceHigh = true;

    static private List<StandardCard> fillDeck(boolean isAceHigh) {
        List<StandardCard> cards = new ArrayList<>();

        for (StandardCard.CardSuit suit : StandardCard.CardSuit.values()) {
            for (StandardCard.CardRank rank : StandardCard.CardRank.values()) {
                cards.add(new StandardCard(rank, suit, isAceHigh));
            }
        }

        return cards;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int add(StandardCard card) {
        List<StandardCard> newCards = new ArrayList<>(this.cards);
        newCards.add(card);
        this.cards = newCards;
        gc();
        return this.cards.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int add(List<StandardCard> cards) {
        List<StandardCard> newCards = new ArrayList<>(this.cards);
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
        List<StandardCard> shuffledCards = new ArrayList<>(this.cards);
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

        List<StandardCard> newCards = new ArrayList<>();
        List<StandardCard> topCards = cards.subList(0, n);
        List<StandardCard> bottomCards = cards.subList(n, this.cards.size());

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
    public StandardCard draw() {
        if (topCardIndex >= cards.size()) {
            return null;
        }

        StandardCard drawnCard = cards.get(topCardIndex);
        topCardIndex++;
        return drawnCard;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<StandardCard> draw(int n) {
        var cap = Math.min(n, cards.size());
        List<StandardCard> drawnCards = new ArrayList<>(cap);

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
