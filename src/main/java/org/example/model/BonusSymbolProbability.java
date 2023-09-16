package org.example.model;

import org.example.model.enumeration.Symbol;

import java.util.Map;

public class BonusSymbolProbability {

    private Map<Symbol, Integer> symbols;

    public Map<Symbol, Integer> getSymbols() {
        return symbols;
    }

    public void setSymbols(Map<Symbol, Integer> symbols) {
        this.symbols = symbols;
    }
}