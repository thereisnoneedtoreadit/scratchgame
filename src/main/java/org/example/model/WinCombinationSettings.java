package org.example.model;

import org.example.model.enumeration.WinCombinationGroup;

import java.util.List;

public class WinCombinationSettings {

    private Float reward_multiplier;
    private String when;
    private Integer count;
    private WinCombinationGroup group;
    private List<List<String>> covered_areas;

    public Float getReward_multiplier() {
        return reward_multiplier;
    }

    public void setReward_multiplier(Float reward_multiplier) {
        this.reward_multiplier = reward_multiplier;
    }

    public String getWhen() {
        return when;
    }

    public void setWhen(String when) {
        this.when = when;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<List<String>> getCovered_areas() {
        return covered_areas;
    }

    public void setCovered_areas(List<List<String>> covered_areas) {
        this.covered_areas = covered_areas;
    }

    public WinCombinationGroup getGroup() {
        return group;
    }

    public void setGroup(WinCombinationGroup group) {
        this.group = group;
    }
}
