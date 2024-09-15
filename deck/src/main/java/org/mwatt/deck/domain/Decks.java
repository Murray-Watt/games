package org.mwatt.deck.domain;

import lombok.Getter;
import java.util.*;

/*
 TODO: Limit the number of Decks and rotate key
 */
public class Decks {
    @Getter
    private static final Decks instance = new Decks();
    private static final Map<Integer,Deck> currentDecks = new HashMap<>();
    private static Integer nextKey = 1000;

    public int addDeck() {
        currentDecks.put(nextKey,DeckImpl.builder().build());
        return nextKey++;
    }

    public boolean removeDeck(int key) {
        if (!currentDecks.containsKey(key)) {
            return false;
        }

        currentDecks.remove(key);
        return true;
    }

    public Deck getDeck(int key) {
        if (!currentDecks.containsKey(key)) {
            return null;
        }

        return currentDecks.get(key);
    }

    public Collection<Deck> getDecks() {
        return currentDecks.values();
    }

    public void clear() {
        currentDecks.clear();
        nextKey = 1000;
    }
}
