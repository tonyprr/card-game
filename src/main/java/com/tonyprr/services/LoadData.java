package com.tonyprr.services;

import java.util.stream.Stream;

public interface LoadData {
    Stream<String> getData(String params) throws Exception;
}
