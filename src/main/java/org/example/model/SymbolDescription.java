package org.example.model;

import org.example.model.enumeration.Impact;
import org.example.model.enumeration.SymbolType;

public class SymbolDescription {

    private Float reward_multiplier;
    private SymbolType type;
    private Impact impact;
    private Integer extra;

    public Float getReward_multiplier() {
        return reward_multiplier;
    }

    public void setReward_multiplier(Float reward_multiplier) {
        this.reward_multiplier = reward_multiplier;
    }

    public SymbolType getType() {
        return type;
    }

    public void setType(SymbolType type) {
        this.type = type;
    }

    public Impact getImpact() {
        return impact;
    }

    public void setImpact(Impact impact) {
        this.impact = impact;
    }

    public Integer getExtra() {
        return extra;
    }

    public void setExtra(Integer extra) {
        this.extra = extra;
    }

}