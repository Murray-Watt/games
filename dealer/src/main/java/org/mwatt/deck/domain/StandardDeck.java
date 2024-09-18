package org.mwatt.deck.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
public class StandardDeck extends CardSeqBase<StandardCard> {
    @Builder.Default
    private boolean isAceHigh = true;

    // Careful two stage initialization
    void initializeDeck(boolean isAceHigh) {
        for (StandardCard.CardSuit suit : StandardCard.CardSuit.values()) {
            for (StandardCard.CardRank rank : StandardCard.CardRank.values()) {
                this.add(new StandardCard(rank, suit, isAceHigh));
            }
        }
    }
}
