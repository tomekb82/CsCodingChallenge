package com.itunes.services;

import com.itunes.model.Track;
import org.json.simple.JSONObject;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

public interface IJsonProcessing {
    Map<String, Track> readJsonFile(String filename,
                                    Function<JSONObject, Long> key,
                                    Function<JSONObject, Track> value,
                                    Consumer<Map.Entry<String, Track>> printInDebugMode);
}
