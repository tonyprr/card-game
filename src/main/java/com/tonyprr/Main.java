package com.tonyprr;

import com.tonyprr.model.Result;
import com.tonyprr.services.CardRulesPoker;
import com.tonyprr.services.LoadDataFromFile;
import com.tonyprr.game.CardGame;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws Exception {
        LoadDataFromFile loadDataFromFile = new LoadDataFromFile();
        CardRulesPoker pokerRules = new CardRulesPoker();

        CardGame cardGame = new CardGame(loadDataFromFile, pokerRules);
        Result result = cardGame.runGame(args[0]);

        Path path = Paths.get(args[1]);

        try (BufferedWriter writer = Files.newBufferedWriter(path))
        {
            result.getPlayers().forEach(player ->
            {
                try {
                    writer.write(player.getName() + ": " + player.getQuantityWon());
                    writer.newLine();
                } catch (IOException e) {
                }
            });
            writer.write("Neither Won: " + result.getNeitherWon());
        }
    }
}
