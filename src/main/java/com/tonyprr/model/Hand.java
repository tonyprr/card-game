package com.tonyprr.model;

import com.tonyprr.library.ValuesOfCardEnum;
import java.util.ArrayList;
import java.util.List;

public class Hand {
    private int score;
    private final List<Card> cards = new ArrayList<>();

    public Hand(List<String> cards) {
        this.score = 0;
        cards.forEach(s -> {
            Card card = new Card();
            card.setValue(
                    ValuesOfCardEnum.stream()
                        .filter(valuesOfCardEnum ->
                            valuesOfCardEnum.getCode()
                                    .equals(String.valueOf(s.charAt(0))))
                        .findFirst().get().getValue()
            );
            card.setSuit(String.valueOf(s.charAt(1)));
            this.cards.add(card);
        });
    }

    public List<Card> getCards() {
        return cards;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
