package org.example.model.enumeration;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Symbol {
    A("A"),
    B("B"),
    C("C"),
    D("D"),
    E("E"),
    F("F"),
    TEN_X("10x"),
    FIVE_X("5x"),
    PLUS_ONE_THOUSAND("+1000"),
    PLUS_FIVE_HUNDREDS("+500"),
    MISS("MISS");

    @JsonValue
    public final String jsonValue;

    Symbol(String jsonValue) {
        this.jsonValue = jsonValue;
    }

}