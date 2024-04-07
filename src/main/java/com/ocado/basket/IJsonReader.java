package com.ocado.basket;

import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface IJsonReader {
    <T> T readFromJson(String path, TypeReference<T> type) throws IOException;
}