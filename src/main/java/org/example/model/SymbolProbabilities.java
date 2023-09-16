package org.example.model;

import java.util.List;

public class SymbolProbabilities {

    private List<StandardSymbolProbability> standard_symbols;
    private BonusSymbolProbability bonus_symbols;

    public List<StandardSymbolProbability> getStandard_symbols() {
        return standard_symbols;
    }

    public void setStandard_symbols(List<StandardSymbolProbability> standard_symbols) {
        this.standard_symbols = standard_symbols;
    }

    public BonusSymbolProbability getBonus_symbols() {
        return bonus_symbols;
    }

    public void setBonus_symbols(BonusSymbolProbability bonus_symbols) {
        this.bonus_symbols = bonus_symbols;
    }

}