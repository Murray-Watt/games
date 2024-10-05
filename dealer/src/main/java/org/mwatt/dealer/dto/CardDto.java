package org.mwatt.dealer.dto;

import lombok.*;
import org.mwatt.dealer.domain.StandardCard;

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
