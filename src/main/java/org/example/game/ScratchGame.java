package org.example.game;

import org.example.model.*;
import org.example.model.enumeration.Impact;
import org.example.model.enumeration.Symbol;
import org.example.model.enumeration.WinCombination;

import java.util.*;
import java.util.stream.Collectors;

public class ScratchGame {

    // dynamic matrix is not supported
    private static final int COLUMNS = 3;
    private static final int ROWS = 3;

    private final GameConfiguration gameConfiguration;

    public ScratchGame(GameConfiguration gameConfiguration) {
        this.gameConfiguration = gameConfiguration;
    }

    public GameResult play(int bettingAmount) {
        Symbol[][] matrix = new Symbol[ROWS][COLUMNS];
        Symbol bonus = insertBonus(matrix);
        SymbolDescription bonusDesc = gameConfiguration.getSymbols().get(bonus);
        Map<Symbol, Integer> symbolCounts = insertStandardSymbols(matrix);
        Map<Symbol, List<WinCombination>> winCombinations = getWinCombinations(symbolCounts);
        int reward = getReward(bettingAmount, winCombinations, bonus);

        GameResult result = new GameResult();
        result.setMatrix(matrix);
        result.setReward(reward);
        result.setApplied_winning_combinations(winCombinations);
        result.setApplied_bonus_symbol(
                bonusDesc.getImpact().equals(Impact.miss) ? null : bonus
        );

        return result;
    }

    private Symbol insertBonus(Symbol[][] matrix) {
        Symbol bonus = getProbableSymbol(
                gameConfiguration
                        .getProbabilities()
                        .getBonus_symbols()
                        .getSymbols()
        );
        matrix[random(0, ROWS)][random(0, COLUMNS)] = bonus;
        return bonus;
    }

    private Symbol getProbableStandardSymbol(int row, int column) {
        StandardSymbolProbability probability = gameConfiguration
                .getProbabilities()
                .getStandard_symbols()
                .stream()
                .filter(s -> s.getColumn() == column && s.getRow() == row)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Standard symbol probability is not defined for cell " + row + ":" + column));
        return getProbableSymbol(probability.getSymbols());
    }

    private int getReward(int bettingAmount, Map<Symbol, List<WinCombination>> winCombinations, Symbol bonus) {
        float reward = bettingAmount;
        float multiplier = 0;
        if (!winCombinations.isEmpty()) {
            for (Map.Entry<Symbol, List<WinCombination>> entry : winCombinations.entrySet()) {
                Symbol s = entry.getKey();
                List<WinCombination> w = entry.getValue();
                float _multiplier = 1;

                Float symbolMultiplier = gameConfiguration.getSymbols().get(s).getReward_multiplier();
                if (symbolMultiplier != null) _multiplier *= symbolMultiplier;

                for (WinCombination winCombination : w) {
                    Float combinationMultiplier = gameConfiguration.getWin_combinations().get(winCombination).getReward_multiplier();
                    if (combinationMultiplier != null) _multiplier *= combinationMultiplier;
                }

                multiplier += _multiplier;
            }

            reward *= multiplier;

            SymbolDescription bonusDesc = gameConfiguration.getSymbols().get(bonus);
            switch (bonusDesc.getImpact()) {
                case multiply_reward -> {
                    if (bonusDesc.getReward_multiplier() != null) reward *= bonusDesc.getReward_multiplier();
                }
                case extra_bonus -> {
                    if (bonusDesc.getExtra() != null) reward += bonusDesc.getExtra();
                }
            }
        }
        return (int) reward;
    }

    private Map<Symbol, List<WinCombination>> getWinCombinations(Map<Symbol, Integer> symbolCounts) {
        Map<Symbol, List<WinCombination>> winCombinations = new HashMap<>();
        gameConfiguration
                .getWin_combinations()
                .entrySet()
                .stream()
                .collect(Collectors.groupingBy(e -> e.getValue().getGroup()))
                .forEach((group, combinations) -> {
                    switch (group) {
                        // only same same_symbols group is supported
                        case same_symbols -> {
                            combinations.sort((c1, c2) -> c2.getValue().getCount() - c1.getValue().getCount());
                            combinations.forEach(e -> {
                                WinCombination combination = e.getKey();
                                WinCombinationSettings settings = e.getValue();
                                symbolCounts.forEach((s, c) -> {
                                    winCombinations.computeIfPresent(s, (k, v) -> {
                                        v.add(combination);
                                        return v;
                                    });
                                    if (Objects.equals(c, settings.getCount())) {
                                        winCombinations.putIfAbsent(s, new ArrayList<>() {{
                                            add(combination);
                                        }});
                                    }
                                });
                            });
                        }
                    }
                });
        return winCombinations;
    }

    private Map<Symbol, Integer> insertStandardSymbols(Symbol[][] matrix) {
        Map<Symbol, Integer> symbolCounts = new HashMap<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = matrix[i][j] == null ? getProbableStandardSymbol(i, j) : matrix[i][j];

                Integer symbolCount = symbolCounts.get(matrix[i][j]);
                if (symbolCount == null) symbolCounts.put(matrix[i][j], 1);
                else symbolCounts.put(matrix[i][j], ++symbolCount);
            }
        }
        return symbolCounts;
    }

    private Symbol getProbableSymbol(Map<Symbol, Integer> probabilities) {
        LinkedHashMap<Symbol, Integer> probabilitiesPrefixesSum = new LinkedHashMap<>(probabilities);

        int sum = 0;
        for (Map.Entry<Symbol, Integer> e : probabilities.entrySet()) {
            Symbol symbol = e.getKey();
            Integer prob = e.getValue();
            sum += prob;
            probabilitiesPrefixesSum.put(symbol, sum);
        }
        int randomProbability = random(0, sum);

        return probabilitiesPrefixesSum
                .entrySet()
                .stream()
                .filter(p -> randomProbability <= p.getValue())
                .findFirst()
                .get()
                .getKey();
    }

    private int random(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

}