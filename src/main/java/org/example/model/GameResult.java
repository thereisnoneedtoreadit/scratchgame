package org.example.model;

import org.example.model.enumeration.Symbol;
import org.example.model.enumeration.WinCombination;

import java.util.List;
import java.util.Map;

public class GameResult {

    private Symbol[][] matrix;
    private Integer reward;
    private Map<Symbol, List<WinCombination>> applied_winning_combinations;
    private Symbol applied_bonus_symbol;

    public Symbol[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(Symbol[][] matrix) {
        this.matrix = matrix;
    }

    public Integer getReward() {
        return reward;
    }

    public void setReward(Integer reward) {
        this.reward = reward;
    }

    public Map<Symbol, List<WinCombination>> getApplied_winning_combinations() {
        return applied_winning_combinations;
    }

    public void setApplied_winning_combinations(Map<Symbol, List<WinCombination>> applied_winning_combinations) {
        this.applied_winning_combinations = applied_winning_combinations;
    }

    public Symbol getApplied_bonus_symbol() {
        return applied_bonus_symbol;
    }

    public void setApplied_bonus_symbol(Symbol applied_bonus_symbol) {
        this.applied_bonus_symbol = applied_bonus_symbol;
    }
}