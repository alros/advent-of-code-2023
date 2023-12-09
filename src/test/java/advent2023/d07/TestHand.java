package advent2023.d07;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestHand {

    @Test
    public void testTypes() {
        assertEquals(Hand.FIVE_OF_A_KIND, new Hand("JJJJJ", 1).type());
        assertEquals(Hand.FOUR_OF_A_KIND, new Hand("222K2", 1).type());
        assertEquals(Hand.THREE_OF_A_KIND, new Hand("72737", 1).type());
        assertEquals(Hand.FULL_HOUSE, new Hand("72772", 1).type());
        assertEquals(Hand.TWO_PAIR, new Hand("A2772", 1).type());
        assertEquals(Hand.ONE_PAIR, new Hand("A277T", 1).type());
        assertEquals(Hand.HIGH_CARD, new Hand("A278T", 1).type());
    }

    //A, K, Q, J, T, 9, 8, 7, 6, 5, 4, 3, or 2.
    @Test
    public void testSort(){
        assertTrue(new Hand("AAAAA",1).compareTo(new Hand("AAAAA",1))==0);
        assertTrue(new Hand("AAAAA",1).compareTo(new Hand("KKKKK",1))>0);
        assertTrue(new Hand("KKKKK",1).compareTo(new Hand("AAAAA",1))<0);
        assertTrue(new Hand("KKKKK",1).compareTo(new Hand("QQQQQ",1))>0);
        assertTrue(new Hand("JJJJJ",1).compareTo(new Hand("QQQQQ",1))<0);
        assertTrue(new Hand("JJJJJ",1).compareTo(new Hand("TTTTT",1))>0);
        assertTrue(new Hand("99999",1).compareTo(new Hand("TTTTT",1))<0);
        assertTrue(new Hand("99999",1).compareTo(new Hand("88888",1))>0);
        assertTrue(new Hand("77777",1).compareTo(new Hand("88888",1))<0);
        assertTrue(new Hand("77777",1).compareTo(new Hand("66666",1))>0);
        assertTrue(new Hand("55555",1).compareTo(new Hand("66666",1))<0);
        assertTrue(new Hand("55555",1).compareTo(new Hand("44444",1))>0);
        assertTrue(new Hand("33333",1).compareTo(new Hand("44444",1))<0);
        assertTrue(new Hand("33333",1).compareTo(new Hand("22222",1))>0);

        assertTrue(new Hand("88991",1).compareTo(new Hand("88199",1))>0);
        assertTrue(new Hand("88999",1).compareTo(new Hand("89998",1))<0);
        assertTrue(new Hand("33332",1).compareTo(new Hand("2AAAA",1))>0);
        assertTrue(new Hand("77888",1).compareTo(new Hand("77788",1))>0);

        assertTrue(new Hand("Q2Q2Q",1).compareTo(new Hand("2JJJJ",1))<0);
    }
}
