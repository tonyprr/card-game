package com.tonyprr.game;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.tonyprr.game.CardGame;
import com.tonyprr.model.Result;
import java.util.ArrayList;
import java.util.List;

import com.tonyprr.services.CardRules;
import com.tonyprr.services.LoadData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("Test Suite of Poker Game.")
@ExtendWith(MockitoExtension.class)
class CardGameTest {

    @Mock
    LoadData loadData;

    @Mock
    CardRules cardRules;

    @InjectMocks
    CardGame cardGame;

    @BeforeEach
    void setUp() {
    }

    @Test
    void runGame_TwoPlayers() throws Exception {
        List<String> data = new ArrayList<>();
        data.add("8C TS KC 9H 4S 7D 2S 5D 3S AC");
        data.add("TD 8C 4H 7C TC KC 4C 3H 7S KS");

        when(loadData.getData(any()))
                .thenReturn(data.stream());
        Result result = cardGame.runGame("data.txt");
        Assertions.assertEquals(2, result.getPlayers().size());
    }

    @Test
    void runGame_ThreePlayers() throws Exception {
        List<String> data = new ArrayList<>();
        data.add("8C TS KC 9H 4S 7D 2S 5D 3S AC 7H 7D 8D 8C 8H");

        when(loadData.getData(any()))
                .thenReturn(data.stream());
        Result result = cardGame.runGame("data.txt");
        Assertions.assertEquals(3, result.getPlayers().size());
    }
}