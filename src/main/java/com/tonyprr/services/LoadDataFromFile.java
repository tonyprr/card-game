package com.tonyprr.services;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class LoadDataFromFile implements LoadData {

    @Override
    public Stream<String> getData(String params) throws Exception {
        Path path = Paths.get(getClass().getClassLoader()
                .getResource(params).toURI());
        return Files.lines(path);
    }
}
