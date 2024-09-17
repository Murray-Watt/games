package org.mwatt.deck.domain;

import lombok.Getter;
import java.util.*;

/*
 TODO: Limit the number of Decks and rotate key
 TODO: Rework given changes with CardSeq
 */
public class Decks {
    @Getter
    private static final Decks instance = new Decks();
    private static final Map<Integer, CardSeq<StandardCard>> currentDecks = new HashMap<>();
    private static Integer nextKey = 1000;

    public int addDeck() {
        currentDecks.put(nextKey, StandardDeck.builder().build());
        return nextKey++;
    }

    public boolean removeDeck(int key) {
        if (!currentDecks.containsKey(key)) {
            return false;
        }

        currentDecks.remove(key);
        return true;
    }

    public CardSeq<StandardCard> getDeck(int key) {
        if (!currentDecks.containsKey(key)) {
            return null;
        }

        return currentDecks.get(key);
    }

    public Collection<CardSeq<StandardCard>> getDecks() {
        return currentDecks.values();
    }

    public void clear() {
        currentDecks.clear();
        nextKey = 1000;
    }
}
