package com.tonyprr.services;

import com.tonyprr.library.Utils;
import com.tonyprr.library.ValuesOfCardEnum;
import com.tonyprr.model.Card;
import com.tonyprr.model.Hand;
import com.tonyprr.model.ResultApply;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CardRulesPoker implements CardRules {

    private final static int score = 1;

    @Override
    public void processWinner(List<Hand> hands) {
        Hand winHand = null;
        for (int i = 1; i < hands.size(); i++) {
            if (i == 1) {
                winHand = compareHands(hands.get(0), hands.get(i));
            } else {
                if (winHand == null) {
                    winHand = hands.get(i - 1);
                }
                winHand = compareHands(winHand, hands.get(i));
            }
        }
        if (winHand != null) {
            winHand.setScore(score);
        }
    }

    private Hand compareHands(Hand firstHand, Hand secondHand) {
        Hand winHand;

        winHand = Utils.appliedRule(firstHand, secondHand, this::isRoyalFlush);
        if (winHand != null) {
            return winHand;
        }

        winHand = Utils.appliedRule(firstHand, secondHand, this::isStraightFlush);
        if (winHand != null) {
            return winHand;
        }

        winHand = Utils.appliedRule(firstHand, secondHand, this::isFourOfAKind);
        if (winHand != null) {
            return winHand;
        }

        winHand = Utils.appliedRule(firstHand, secondHand, this::isFullHouse);
        if (winHand != null) {
            return winHand;
        }

        winHand = Utils.appliedRule(firstHand, secondHand, this::isFlush);
        if (winHand != null) {
            return winHand;
        }

        winHand = Utils.appliedRule(firstHand, secondHand, this::isStraight);
        if (winHand != null) {
            return winHand;
        }

        winHand = Utils.appliedRule(firstHand, secondHand, this::isThreeOfAKind);
        if (winHand != null) {
            return winHand;
        }

        winHand = Utils.appliedRule(firstHand, secondHand, this::isTwoPairs);
        if (winHand != null) {
            return winHand;
        }

        winHand = Utils.appliedRule(firstHand, secondHand, this::isOnePair);
        if (winHand != null) {
            return winHand;
        }

        winHand = highCard(firstHand, secondHand);
        return winHand;
    }

    private ResultApply isRoyalFlush(Hand hand) {
        ResultApply resultApply = new ResultApply();
        String suit = hand.getCards().get(0).getSuit();
        List<Integer> values = Utils.convertToValueList(hand.getCards());
        resultApply.setApplied(hand.getCards().stream().allMatch(s -> s.getSuit().equals(suit))
                && hand.getCards().stream().allMatch(s ->
                ValuesOfCardEnum.royalList().contains(s.getValue())));
        if (resultApply.isApplied()) {
            resultApply.setValues(values);
        }
        return resultApply;
    }

    private ResultApply isStraightFlush(Hand hand) {
        ResultApply resultApply = new ResultApply();
        String suit = hand.getCards().get(0).getSuit();
        List<Integer> values = Utils.convertToValueList(hand.getCards());
        resultApply.setApplied(hand.getCards().stream().allMatch(s -> s.getSuit().equals(suit))
                && isConsecuteCards(values));
        if (resultApply.isApplied()) {
            resultApply.setValues(values);
        }
        return resultApply;
    }

    private ResultApply isFourOfAKind(Hand hand) {
        ResultApply resultApply = new ResultApply();
        List<Integer> values = numSameValues(hand.getCards());
        resultApply.setApplied(values.size() == 3);
        if (resultApply.isApplied()) {
            resultApply.setValues(values);
        }
        return resultApply;
    }

    private ResultApply isFullHouse(Hand hand) {
        ResultApply resultApply = new ResultApply();
        List<Integer> values = valuesDiffIsZero(hand.getCards());
        resultApply.setApplied(values.stream().distinct().count() == 2 && values.stream().count() == 3);
        if (resultApply.isApplied()) {
            List<Integer> valuesDistinct = values.stream().distinct().collect(Collectors.toList());
            int count1 = (int) values.stream().filter(value -> value == valuesDistinct.get(0)).count();
            int count2 = (int) values.stream().filter(value -> value == valuesDistinct.get(1)).count();
            if (count1 > count2) {
                resultApply.setValues(Collections.singletonList(valuesDistinct.get(0)));
            } else {
                resultApply.setValues(Collections.singletonList(valuesDistinct.get(1)));
            }
        }
        return resultApply;
    }


    private ResultApply isFlush(Hand hand) {
        ResultApply resultApply = new ResultApply();
        String suit = hand.getCards().get(0).getSuit();
        resultApply.setApplied(hand.getCards().stream().allMatch(s -> s.getSuit().equals(suit)));
        if (resultApply.isApplied()) {
            resultApply.setValues(Utils.convertToValueList(hand.getCards()));
        }
        return resultApply;
    }

    private ResultApply isStraight(Hand hand) {
        ResultApply resultApply = new ResultApply();
        List<Integer> values = Utils.convertToValueList(hand.getCards());
        resultApply.setApplied(isConsecuteCards(values));
        if (resultApply.isApplied()) {
            resultApply.setValues(values);
        }
        return resultApply;
    }

    private ResultApply isThreeOfAKind(Hand hand) {
        ResultApply resultApply = new ResultApply();
        List<Integer> values = numSameValues(hand.getCards());
        resultApply.setApplied(values.size() == 2);
        if (resultApply.isApplied()) {
            resultApply.setValues(values);
        }
        return resultApply;
    }

    private ResultApply isTwoPairs(Hand hand) {
        ResultApply resultApply = new ResultApply();
        List<Integer> values = valuesDiffIsZero(hand.getCards());
        resultApply.setApplied(values.stream().distinct().count() == 2 && values.stream().count() == 2);
        if (resultApply.isApplied()) {
            resultApply.setValues(values);
        }
        return resultApply;
    }


    private ResultApply isOnePair(Hand hand) {
        ResultApply resultApply = new ResultApply();
        List<Integer> values = numSameValues(hand.getCards());
        resultApply.setApplied(values.size() == 1);
        if (resultApply.isApplied()) {
            resultApply.setValues(values);
        }
        return resultApply;
    }

    private Hand highCard(Hand firstHand, Hand secondHand) {
        int indexMax = Utils.highCardEvaluate(
                Utils.convertToValueList(firstHand.getCards()),
                Utils.convertToValueList(secondHand.getCards()));
        if (indexMax == 1) {
            return firstHand;
        } else if (indexMax == 2) {
            return secondHand;
        } else {
            return null;
        }
    }

    private List<Integer> valuesDiffIsZero(List<Card> cards1) {
        List<Integer> values = Utils.convertToValueList(cards1);
        AtomicReference<Integer> index = new AtomicReference<>(0);

        return IntStream.range(0, values.size() - 1)
            .map(i -> {
                index.set(i);
                return values.get(i + 1) - values.get(i);
            })
            .filter(value -> value == 0)
            .mapToObj(operand -> values.get(index.get()))
            .collect(Collectors.toList());
    }

    private List<Integer> numSameValues(List<Card> cards1) {
        List<Integer> values = valuesDiffIsZero(cards1);
        return values.stream().distinct().count() == 1
                ? values : Collections.EMPTY_LIST;
    }

    private boolean isConsecuteCards(List<Integer> values) {
        return IntStream.range(0, values.size() - 1)
                .map(i -> values.get(i + 1) - values.get(i))
                .max().getAsInt() == 1;
    }
}
