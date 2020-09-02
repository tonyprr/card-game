package com.tonyprr.library;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public enum ValuesOfCardEnum {
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("T", 10),
    ELEVEN("J", 11),
    TWELVE("Q", 12),
    THIRTEEN("K", 13),
    ACE("A", 14);

    private String code;
    private int value;

    ValuesOfCardEnum(String code, int value) {
        this.code = code;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public int getValue() {
        return value;
    }

    public static Stream<ValuesOfCardEnum> stream() {
        return Stream.of(ValuesOfCardEnum.values());
    }

    public static List<Integer> royalList() {
        return Arrays.asList(TEN.value, ELEVEN.value, TWELVE.value, THIRTEEN.value, ACE.value);
    }
}
