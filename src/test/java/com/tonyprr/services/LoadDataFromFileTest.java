package com.tonyprr.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.stream.Stream;

@DisplayName("Test Suite of LoadDataFromFile.")
public class LoadDataFromFileTest {

    @Test
    void getData_ThreeRegisters() throws Exception {
        LoadDataFromFile loadDataFromFile = new LoadDataFromFile();
        Stream<String> data = loadDataFromFile.getData("pokerdata.txt");

        Assertions.assertEquals(3, data.count());
    }

}
