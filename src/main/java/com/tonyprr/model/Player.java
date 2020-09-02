package com.tonyprr.model;

import java.math.BigDecimal;
import java.util.List;

public class Player {
    private String name;
    private int quantityWon;
    private BigDecimal probabilityOfWinning;

    public Player(int i) {
        name = "Player " + (i+1);
        quantityWon = 0;
    }

    public int getQuantityWon() {
        return quantityWon;
    }

    public void setQuantityWon(int quantityWon) {
        this.quantityWon = quantityWon;
    }

    public BigDecimal getProbabilityOfWinning() {
        return probabilityOfWinning;
    }

    public void setProbabilityOfWinning(BigDecimal probabilityOfWinning) {
        this.probabilityOfWinning = probabilityOfWinning;
    }

    public String getName() {
        return name;
    }
}
