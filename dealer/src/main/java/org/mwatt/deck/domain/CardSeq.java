package org.mwatt.deck.domain;

import java.util.List;

/**
 * CardList
 * This is common for different groupings of cards
 * The name applies ordering but does not imply a specific access method
 */
public interface CardSeq<T> {

    /**
     * Shuffle randomly reorders the sequence
     */
    void shuffle();

    /**
     * Splits a sequence in two that puts the second parts in front of the first
     * If n >= 0 or n <= seq.size() the cut counts as a tap - null operation (NOOP)
     *
     * @param n: Position of cut where n-1 is the last card in the first part
     *           and n is the position of the second part.
     *
     * @return true iff n is divides sequence in two.
     */
    boolean cut(int n);

    /**
     * Draw a single card from the sequence
     *
     * @return a card if the sequence is not empty
     *         null if the sequence is empty
     */
    T draw();

    /**
     * Draw n cards from the sequence
     *
     * @param n: the number of cards to draw
     * @return n cards if there n or more cards of the sequence
     *         all the cards in the sequence is less than size n
     */
    List<T> draw(int n);

    /**
     * Append a card to the sequence
     *
     * @param card: the card to append
     * @return the new sequence size
     */
    int add(T card);

    /**
     * Append a card to the sequence
     *
     * @param cards: the cards to append
     * @return the new sequence size
     */
    int add(List<T> cards);


    /**
     * Remove the card at position n
     * @param n: The index of the card to remove
     * @return the new sequence size
     */
    int remove(int n);
}
