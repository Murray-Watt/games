package org.mwatt.deck.dto;

import lombok.*;
import org.mwatt.deck.domain.StandardCard;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class CardDto {
    private String rank;
    private String suit;

    public static CardDto toDto(StandardCard card) {
        return new CardDto(card.rank().toString(), card.suit().toString());
    }
}
