package com.tonyprr.game;

import com.tonyprr.library.Utils;
import com.tonyprr.model.Hand;
import com.tonyprr.model.Player;
import com.tonyprr.model.Result;
import com.tonyprr.services.CardRules;
import com.tonyprr.services.LoadData;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CardGame {

    private final LoadData loadData;
    private final CardRules cardRules;

    public CardGame(LoadData loadData, CardRules cardRules) {
        this.loadData = loadData;
        this.cardRules = cardRules;
    }

    public Result runGame(String loadParams) throws Exception {
        Result result = new Result();
        result.setNeitherWon(0);
        result.setPlayers(new ArrayList<>());
        Stream<String> handsData = loadData.getData(loadParams);

        handsData.forEach(handData -> {
            List<Hand> hands = Utils.generateHandList(handData);
            cardRules.processWinner(hands);
            updateResult(result, hands);
        });
        handsData.close();
        return result;
    }

    private void updateResult(Result result, List<Hand> hands) {
        int sizeHands = hands.size();
        int sizePlayers = result.getPlayers().size();
        if (hands.stream().map(Hand::getScore).max(Integer::compareTo).get() == 0) {
            result.setNeitherWon(result.getNeitherWon() + 1);
        }
        IntStream.range(0, sizeHands).forEach(i -> {
            if (i < sizePlayers) {
                result.getPlayers().get(i).setQuantityWon(
                        result.getPlayers().get(i).getQuantityWon()
                        + hands.get(i).getScore()
                );
            } else {
                Player player = new Player(i);
                player.setQuantityWon(hands.get(i).getScore());
                result.getPlayers().add(player);
            }
        });
    }
}
