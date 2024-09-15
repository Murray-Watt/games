package org.mwatt.deck.domain;

import java.util.List;

public interface Deck {
    String toJson();
    void shuffle();
    void cut(int n);
    Card draw();
    List<Card> draw(int n);
    void add(Card card);
    void add(List<Card> cards);
}
