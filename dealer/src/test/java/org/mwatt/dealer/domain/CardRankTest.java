package org.mwatt.dealer.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class CardRankTest {

    @ParameterizedTest
    @CsvSource({
            "TWO,  KING, false, -1",
            "TWO,  TWO,  false, 0",
            "KING, TWO,  false, 1",
    })

    public void compareToHappyPath(StandardCard.CardRank rank1, StandardCard.CardRank rank2, boolean isAceHigh, int expected) {

        int result = rank1.compareTo(rank2, isAceHigh);
        var errorMsg = STR."Expected comparison result \{expected} but was \{result} for ranks \{rank1} and \{rank2} with isAceHigh \{isAceHigh}";

        Assertions.assertEquals(expected, result,errorMsg);
    }

    @ParameterizedTest
    @CsvSource({
            "ACE, TWO, true, 1",
            "ACE, KING, true, 1",
            "TWO, ACE, true, -1",
            "KING, TWO, false, 1",
            "TWO, THREE, false, -1",
            "THREE, TWO, false, 1"
    })

    public void compareToAceCardRank(StandardCard.CardRank rank1, StandardCard.CardRank rank2, boolean isAceHigh, int expected) {

        int result = rank1.compareTo(rank2, isAceHigh);
        var errorMsg = STR."Expected comparison result \{expected} but was \{result} for ranks \{rank1} and \{rank2} with isAceHigh \{isAceHigh}";

        Assertions.assertEquals(expected, result,errorMsg);
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    public void testCompareToSameRank(boolean isAceHigh) {
        int result = StandardCard.CardRank.TWO.compareTo(StandardCard.CardRank.TWO, isAceHigh);
        Assertions.assertEquals(0, result);
    }
}