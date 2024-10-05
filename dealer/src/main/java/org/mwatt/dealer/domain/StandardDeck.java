package org.mwatt.dealer.domain;

import lombok.Builder;
import lombok.Getter;

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
