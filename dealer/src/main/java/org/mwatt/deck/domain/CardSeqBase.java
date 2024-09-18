package org.mwatt.deck.domain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/*
   An optimization I got from Charter discussion
   If I make the last card in the ArrayList the top it will be more efficient.
   This will be more efficient for arrays also.
 */
@Getter
public class CardSeqBase<T> implements CardSeq<T> {

    protected List<T> cards = new ArrayList<>();

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

    /*
        TODO: Check if shuffle collection is sufficiently random.
     */
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
