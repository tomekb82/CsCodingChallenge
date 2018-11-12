package com.itunes.services;

import com.itunes.CsCodingChallenge;
import com.itunes.model.ErrorMessage;
import com.itunes.model.Track;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.itunes.CsCodingChallenge.JSON_FILE_FORMAT;

public class JsonProcessing implements IJsonProcessing {

    public static final String RESULTS = "results";
    public static final String DEBUG_PROPERTY = "debug";
    public static final String PROPERTY_VALUE_TRUE = "true";

    @Override
    public Map<String, Track> readJsonFile(String filename,
                                           Function<JSONObject, Long> key,
                                           Function<JSONObject, Track> value,
                                           Consumer<Map.Entry<String, Track>> printInDebugMode) {
        checkInputParams(filename, key, value);
        File track = getFileReader.apply(filename);
        Map<String, Track> tracks = parseJsonFile(key, value, track);

        if(isDebugMode() && printInDebugMode!= null) {
            tracks.entrySet().stream().forEach(printInDebugMode);
        }

        return tracks;
    }

    protected void checkInputParams(String filename,
                                  Function<JSONObject, Long> key,
                                  Function<JSONObject, Track> value){
        if(!filename.endsWith(JSON_FILE_FORMAT)){
            throw new RuntimeException(ErrorMessage.INVALID_FILE_FORMAT.toString());
        }

        if(key == null || value == null){
            throw new RuntimeException(ErrorMessage.PARAMETER_REQUIRED.toString());
        }
    }

    protected Map<String, Track> parseJsonFile(Function<JSONObject, Long> key, Function<JSONObject, Track> value, File track) {
        Map<String, Track> tracks;
        JSONParser parser = new JSONParser();
        try (Reader is = new FileReader(track)) {
            JSONObject jsonObject = (JSONObject) parser.parse(is);
            JSONArray jsonArray = (JSONArray) jsonObject.get(RESULTS);
            tracks = (Map<String, Track>) jsonArray.stream().collect(
                    Collectors.toMap(key, value));
        } catch (IOException | ParseException e) {
            throw new RuntimeException(ErrorMessage.FILE_NOT_EXIST.value(), e);
        }
        return tracks;
    }

    /**
     * Retrieve a file with specified name
     */
    public static Function<String, File> getFileReader = filename -> {
        File file = new File(filename);
        return file;
    };

    private boolean isDebugMode() {
        return System.getProperty(DEBUG_PROPERTY)!=null
                && System.getProperty(DEBUG_PROPERTY).equals(PROPERTY_VALUE_TRUE);
    }

}
