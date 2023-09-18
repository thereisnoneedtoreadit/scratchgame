package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.game.ScratchGame;
import org.example.model.GameConfiguration;
import org.example.model.GameResult;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

public class Main {

    private static final String CONFIG_ARG = "--config";
    private static final String BETTING_AMOUNT_ARG = "--betting-amount";

    public static void main(String[] args) throws IOException {
        String configPath = null;
        Integer bettingAmount = null;

        for (int i = 0; i < args.length; i++) {
            if (Objects.equals(args[i], CONFIG_ARG)) configPath = args[i + 1];
            if (Objects.equals(args[i], BETTING_AMOUNT_ARG)) bettingAmount = Integer.valueOf(args[i + 1]);
        }
        if (configPath == null || bettingAmount == null)
            throw new IllegalStateException("'config' and 'betting-amount' args should be provided");

        ObjectMapper mapper = new ObjectMapper();
        GameConfiguration gameConfiguration = mapper.readValue(Files.readAllBytes(Paths.get(configPath)), GameConfiguration.class);
        GameResult gameResult = new ScratchGame(gameConfiguration).play(bettingAmount);

        System.out.println(mapper.writeValueAsString(gameResult));
    }

}