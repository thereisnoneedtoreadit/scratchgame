package org.example.game;

import org.example.model.GameConfiguration;

public class ScratchGame {

    private GameConfiguration gameConfiguration;

    public ScratchGame(GameConfiguration gameConfiguration) {
        this.gameConfiguration = gameConfiguration;
    }

    public void start() {
        System.out.println("hello");
    }

}