package org.mwatt.deck.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("preview")
class CardTest {

    @Test
    public void equalsTests() {
        {
            final boolean isAceHigh = true;
            final Card cardA = createCard("ACE of HEARTS", isAceHigh);
            final Card cardB = createCard("ACE of HEARTS", isAceHigh);

            assertEquals(cardA, cardB,
                    () -> STR."Card \{cardA} should equal \{cardB} with isAceHigh=\{isAceHigh}.");
        }

        {
            final boolean isAceHigh = false;
            final Card cardA = createCard("TWO of DIAMONDS", isAceHigh);
            final Card cardB = createCard("THREE of HEARTS", isAceHigh);

            assertNotEquals(cardA, cardB,
                    () -> STR."Card \{cardA} should not equal \{cardB} with isAceHigh=\{isAceHigh}.");
        }

        {
            boolean isAceHigh = true;
            final Card cardA = createCard("ACE of HEARTS", isAceHigh);
            final Card cardB = createCard("ACE of HEARTS", isAceHigh);

            assertEquals(cardA, cardB,
                    () -> STR."Card \{cardA} should equal \{cardB} with isAceHigh=\{isAceHigh}.");
        }
    }


    @Test
    public void hashCodeTests() {
        {
            final boolean isAceHigh = true;
            final Card cardA = createCard("ACE of HEARTS", isAceHigh);
            final Card cardB = createCard("ACE of HEARTS", isAceHigh);

            assertEquals(cardA.hashCode(), cardB.hashCode(),
                    () -> STR."Cards \{cardA} and \{cardB} should have the same hash code with isAceHigh=\{isAceHigh}.");
        }

        {
            final boolean isAceHigh = false;
            final Card cardA = createCard("TWO of DIAMONDS", isAceHigh);
            final Card cardB = createCard("THREE of HEARTS", isAceHigh);

            assertNotEquals(cardA.hashCode(), cardB.hashCode(),
                    () -> STR."Cards \{cardA} and \{cardB} should have different hash codes with isAceHigh=\{isAceHigh}.");
        }
    }

    @Test
    public void compareToTest() {
        {
            boolean isAceHigh = true;
            final Card cardA = createCard("ACE of HEARTS", isAceHigh);
            final Card cardB = createCard("ACE of HEARTS", isAceHigh);

            assertEquals(0, cardA.compareTo(cardB),
                    () -> STR."Cards \{cardA} and \{cardB} should be equal with isAceHigh=\{isAceHigh}.");
        }

        {
            boolean isAceHigh = false;
            final Card cardA = createCard("TWO of DIAMONDS", isAceHigh);
            final Card cardB = createCard("THREE of HEARTS", isAceHigh);

            assertTrue(cardA.compareTo(cardB) < 0,
                    () -> STR."Card \{cardA} should be less than \{cardB} with isAceHigh=\{isAceHigh}.");
        }

        {
            boolean isAceHigh = true;
            final Card cardA = createCard("ACE of HEARTS", isAceHigh);
            final Card cardB = createCard("TWO of HEARTS", isAceHigh);

            assertTrue(cardA.compareTo(cardB) > 0,
                    () -> STR."Card \{cardA} should be greater than \{cardB} with isAceHigh=\{isAceHigh}.");
        }

        {
            boolean isAceHigh = true;
            final Card cardA = createCard("KING of HEARTS", isAceHigh);
            final Card cardB = createCard("ACE of HEARTS", isAceHigh);

            assertFalse(cardA.compareTo(cardB) >= 0,
                    () -> STR."Card \{cardA} should be less than \{cardB} with isAceHigh=\{isAceHigh}.");
        }
    }


    private static Card createCard(String cardString, boolean isAceHigh) {
        String[] parts = cardString.split(" of ");
        return new Card(CardRank.valueOf(parts[0].toUpperCase()), CardSuit.valueOf(parts[1].toUpperCase()),isAceHigh);
    }
}