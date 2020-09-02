package com.tonyprr.library;

import com.tonyprr.model.Card;
import com.tonyprr.model.Hand;
import com.tonyprr.model.ResultApply;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Utils {

    public static List<Integer> convertToValueList(List<Card> cards) {
        return cards.stream()
                .map(Card::getValue)
                .sorted(Integer::compareTo)
                .collect(Collectors.toList());
    }

    public static List<Hand> generateHandList(String handData) {
        List<Hand> result = new ArrayList<>();
        List<String> cards = Arrays.asList(handData.split(" "));
        if (!cards.isEmpty() && cards.size() % 5 == 0) {
            IntStream.range(0, cards.size() / 5)
                    .forEach(i -> result.add(
                            new Hand(cards.subList(i*5, 5*(i + 1))))
                    );
        }
        return result;
    }

    public static Hand appliedRule(Hand hand1, Hand hand2,
                             Function<Hand, ResultApply> func1) {
        ResultApply firstResult = func1.apply(hand1);
        ResultApply secondResult = func1.apply(hand2);

        if (firstResult.isApplied() && !secondResult.isApplied()) {
            return hand1;
        } else if (!firstResult.isApplied() && secondResult.isApplied()) {
            return hand2;
        } else if (firstResult.isApplied() && secondResult.isApplied()) {
            int index = highCardEvaluate(firstResult.getValues(), secondResult.getValues());
            if (index == 1) {
                return hand1;
            } else if (index == 2) {
                return hand2;
            } else {
                index = highCardEvaluate(
                    hand1.getCards().stream().map(Card::getValue)
                        .filter(value -> !firstResult.getValues().contains(value))
                        .collect(Collectors.toList()),
                    hand2.getCards().stream().map(Card::getValue)
                        .filter(value -> !secondResult.getValues().contains(value))
                        .collect(Collectors.toList()));
                if (index == 1) {
                    return hand1;
                } else if (index == 2) {
                    return hand2;
                } else {
                    return null;
                }
            }
        } else {
            return null;
        }
    }

    public static int highCardEvaluate(List<Integer> firstValues, List<Integer> secondValues) {

        if (firstValues.isEmpty()) {
            return 0;
        }

        Integer firstValue = firstValues.stream()
                .max(Integer::compareTo).get();
        Integer secondValue = secondValues.stream()
                .max(Integer::compareTo).get();
        if (firstValue > secondValue) {
            return 1;
        } else if (firstValue < secondValue) {
            return 2;
        } else {
            return highCardEvaluate(
                    firstValues.stream()
                    .filter(card -> !card.equals(firstValue)).collect(Collectors.toList()),
                    secondValues.stream()
                    .filter(card -> !card.equals(secondValue)).collect(Collectors.toList())
            );
        }
    }
}
