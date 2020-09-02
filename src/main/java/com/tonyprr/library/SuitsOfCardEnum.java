package com.tonyprr.library;

import java.util.stream.Stream;

public enum SuitsOfCardEnum {
    DIAMOND("D"),
    HEART("H"),
    CLUB("C"),
    SPADE("S");

    private String suit;

    SuitsOfCardEnum(String suit) {
        this.suit = suit;
    }

    public static Stream<SuitsOfCardEnum> stream() {
        return Stream.of(SuitsOfCardEnum.values());
    }
}
