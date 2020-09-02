package com.tonyprr.services;

import com.tonyprr.library.Utils;
import com.tonyprr.model.Hand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

@DisplayName("Test Suite of CardRulesPoker.")
public class CardRulesPokerTest {

    static CardRulesPoker cardRulesPoker;
    List<Hand> hands;
    String handData;

    @BeforeAll
    static void initAll() {
        cardRulesPoker = new CardRulesPoker();
    }

    @Test
    void royalFlush_TwoPlayers() {
        handData = "TD AD QD KD JD KD 9D 7C AS JS";
        hands = Utils.generateHandList(handData);
        cardRulesPoker.processWinner(hands);
        Assertions.assertEquals(1, hands.get(0).getScore());
    }

    @Test
    void royalFlush_ThreePlayers() {
        handData = "JC 6S 5H 2H 2D KD 9D 7C AS JS TD AD QD KD JD";
        hands = Utils.generateHandList(handData);
        cardRulesPoker.processWinner(hands);
        Assertions.assertEquals(1, hands.get(2).getScore());
    }

    @Test
    void pairAndHigh() {
        handData = "5H 5C 6S 7S KD 2C 3S 8S 8D TD";
        hands = Utils.generateHandList(handData);
        cardRulesPoker.processWinner(hands);
        Assertions.assertEquals(1, hands.get(1).getScore());
    }

    @Test
    void highCard() {
        handData = "5D 8C 9S JS AC 2C 5C 7D 8S QH";
        hands = Utils.generateHandList(handData);
        cardRulesPoker.processWinner(hands);
        Assertions.assertEquals(1, hands.get(0).getScore());
    }

    @Test
    void flushOverThreeOfAKind() {
        handData = "2D 9C AS AH AC 3D 6D 7D TD QD";
        hands = Utils.generateHandList(handData);
        cardRulesPoker.processWinner(hands);
        Assertions.assertEquals(1, hands.get(1).getScore());
    }

    @Test
    void pairOfQueenAndHigh() {
        handData = "4D 6S 9H QH QC 3D 6D 7H QD QS";
        hands = Utils.generateHandList(handData);
        cardRulesPoker.processWinner(hands);
        Assertions.assertEquals(1, hands.get(0).getScore());
    }

    @Test
    void fullHouseAndHighValue() {
        handData = "2H 2D 4C 4D 4S 3C 3D 3S 9S 9D";
        hands = Utils.generateHandList(handData);
        cardRulesPoker.processWinner(hands);
        Assertions.assertEquals(1, hands.get(0).getScore());
    }

    @Test
    void neitherWon() {
        handData = "5D 8C 9S QS AC 8D 5C 9D AS QH";
        hands = Utils.generateHandList(handData);
        cardRulesPoker.processWinner(hands);
        Assertions.assertEquals(0, hands.get(0).getScore());
        Assertions.assertEquals(0, hands.get(1).getScore());
    }
}
