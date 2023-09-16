package org.example.model;

import org.example.model.enumeration.Symbol;

import java.util.Map;

public class StandardSymbolProbability {

    private Integer column;
    private Integer row;
    private Map<Symbol, Integer> symbols;

    public Integer getColumn() {
        return column;
    }

    public void setColumn(Integer column) {
        this.column = column;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Map<Symbol, Integer> getSymbols() {
        return symbols;
    }

    public void setSymbols(Map<Symbol, Integer> symbols) {
        this.symbols = symbols;
    }
}