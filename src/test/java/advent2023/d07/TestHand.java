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

    @Test
    public void withJokers(){
        assertEquals(Hand.FIVE_OF_A_KIND, new Hand("JJJJJ", 1, true).type());
        assertEquals(Hand.FIVE_OF_A_KIND, new Hand("JJJJQ", 1, true).type());
        assertEquals(Hand.FIVE_OF_A_KIND, new Hand("JJJQQ", 1, true).type());
        assertEquals(Hand.FIVE_OF_A_KIND, new Hand("JJQQQ", 1, true).type());
        assertEquals(Hand.FIVE_OF_A_KIND, new Hand("JQQQQ", 1, true).type());

        assertEquals(Hand.FOUR_OF_A_KIND, new Hand("J8QQQ", 1, true).type());
        assertEquals(Hand.FOUR_OF_A_KIND, new Hand("J8JQQ", 1, true).type());
        assertEquals(Hand.FOUR_OF_A_KIND, new Hand("J8JJQ", 1, true).type());
        assertEquals(Hand.FIVE_OF_A_KIND, new Hand("J8JJJ", 1, true).type());

        assertEquals(Hand.FOUR_OF_A_KIND, new Hand("J85JJ", 1, true).type());
        assertEquals(Hand.FOUR_OF_A_KIND, new Hand("J855J", 1, true).type());
        assertEquals(Hand.FOUR_OF_A_KIND, new Hand("J8555", 1, true).type());

        assertEquals(Hand.THREE_OF_A_KIND, new Hand("J8552", 1, true).type());

        assertEquals(Hand.ONE_PAIR, new Hand("J8352", 1, true).type());

        assertEquals(Hand.FULL_HOUSE, new Hand("J8822", 1, true).type());
    }

    @Test
    public void sortWithJokers(){
        Hand a = new Hand("J345A", 1, true);
        Hand b = new Hand("2345J", 1, true);
        assertEquals(Hand.ONE_PAIR, a.type());
        assertEquals(Hand.ONE_PAIR, b.type());
        assertTrue(a.compareTo(b)<0);
    }
}
