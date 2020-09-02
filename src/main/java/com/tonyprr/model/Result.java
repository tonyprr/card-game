package com.tonyprr.model;

import java.util.List;

public class Result {
    private List<Player> players;
    private int neitherWon;

    public int getNeitherWon() {
        return neitherWon;
    }

    public void setNeitherWon(int neitherWon) {
        this.neitherWon = neitherWon;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
