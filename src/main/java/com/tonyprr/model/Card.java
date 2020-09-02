package com.tonyprr.model;

import com.tonyprr.library.SuitsOfCardEnum;

public class Card {
    private int value;
    private String suit;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }
}
