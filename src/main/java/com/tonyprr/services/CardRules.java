package com.tonyprr.services;

import com.tonyprr.model.Hand;
import java.util.List;

public interface CardRules {
    void processWinner(List<Hand> hands);
}
