package org.example.model;

import org.example.model.enumeration.Symbol;
import org.example.model.enumeration.WinCombination;

import java.util.Map;

public class GameConfiguration {

    private Integer columns;
    private Integer rows;
    private Map<Symbol, SymbolDescription> symbols;
    private SymbolProbabilities probabilities;
    private Map<WinCombination, WinCombinationSettings> win_combinations;

    public Integer getColumns() {
        return columns;
    }

    public void setColumns(Integer columns) {
        this.columns = columns;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Map<Symbol, SymbolDescription> getSymbols() {
        return symbols;
    }

    public void setSymbols(Map<Symbol, SymbolDescription> symbols) {
        this.symbols = symbols;
    }

    public SymbolProbabilities getProbabilities() {
        return probabilities;
    }

    public void setProbabilities(SymbolProbabilities probabilities) {
        this.probabilities = probabilities;
    }

    public Map<WinCombination, WinCombinationSettings> getWin_combinations() {
        return win_combinations;
    }

    public void setWin_combinations(Map<WinCombination, WinCombinationSettings> win_combinations) {
        this.win_combinations = win_combinations;
    }
}
