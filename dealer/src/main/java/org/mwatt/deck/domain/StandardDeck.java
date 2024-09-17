package org.mwatt.deck.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
public class StandardDeck extends CardSeqBase<StandardCard> {
    @Builder.Default
    private List<StandardCard> cards = fillDeck(true);
    @Builder.Default
    private boolean isAceHigh = true;

    private static List<StandardCard> fillDeck(boolean isAceHigh) {
        List <StandardCard> cards = new ArrayList<StandardCard>();

        for (StandardCard.CardSuit suit : StandardCard.CardSuit.values()) {
            for (StandardCard.CardRank rank : StandardCard.CardRank.values()) {
                cards.add(new StandardCard(rank, suit, isAceHigh));
            }
        }

        return cards;
    }
}
