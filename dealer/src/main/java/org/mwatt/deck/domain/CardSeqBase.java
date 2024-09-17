package org.mwatt.deck.domain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
public class CardSeqBase<T> implements CardSeq<T> {

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
    private List<T> cards = new ArrayList<>();


    /**
     * {@inheritDoc}
     */
    @Override
    public int add(T card) {
        List<T> newCards = new ArrayList<>(this.cards);
        newCards.add(card);
        this.cards = newCards;
        return this.cards.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int add(List<T> cards) {
        List<T> newCards = new ArrayList<>(this.cards);
        newCards.addAll(cards);
        this.cards = newCards;
        return this.cards.size();
    }

    /**
     * Removes the card at the specified index from this card sequence.
     *
     * @param n the index of the card to be removed
     * @return the number of cards remaining in the sequence after removal
     * @throws IndexOutOfBoundsException if the specified index is out of bounds
     *
     * Alternatively we can return this.cards.size(); the index is out of bound.
     */
    @Override
    public int remove(int n) {
        if (n < 0 || n+1 >= this.cards.size()) {
            throw new IndexOutOfBoundsException();
        }

        this.cards.remove(n);
        return this.cards.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void shuffle() {
        List<T> shuffledCards = new ArrayList<>(this.cards);
        Collections.shuffle(shuffledCards);
        this.cards = shuffledCards;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean cut(int n) {
        if (n <= 0 || n >= this.cards.size()) {
            return false; // NOOP
        }

        List<T> newCards = new ArrayList<>();
        List<T> topCards = cards.subList(0, n);
        List<T> bottomCards = cards.subList(n, this.cards.size());

        newCards.addAll(bottomCards);
        newCards.addAll(topCards);

        this.cards = newCards;
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T draw() {
        if (cards.isEmpty()) {
            return null;
        }

        return cards.removeFirst();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> draw(int n) {
        var cap = Math.min(n, cards.size());
        List<T> drawnCards = new ArrayList<>(cap);

        for (int i = 0; i < cap; i++) {
            var nextCard = draw();
            if (nextCard != null) {
                drawnCards.add(nextCard);
            }
        }

        return drawnCards;
    }
}
