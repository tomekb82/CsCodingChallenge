package com.itunes;

import com.itunes.model.ErrorMessage;
import com.itunes.model.TimeValue;
import com.itunes.model.Track;
import com.itunes.model.TrackField;
import com.itunes.services.Calculations;
import com.itunes.services.ICalculations;
import com.itunes.services.IJsonProcessing;
import com.itunes.services.JsonProcessing;
import com.itunes.util.CalculationsUtil;
import org.json.simple.JSONObject;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CsCodingChallenge {

    public static final String JSON_FILE_FORMAT = ".json";

    public static void main(String[] args) {
        CsCodingChallenge csCodingChallenge = new CsCodingChallenge();
        csCodingChallenge.process(args);
    }

    public void process (String[] args){
        checkInputParams(args);
        Map<String, Track> tracks = readJsonFile(args[0]);
        List<Track> filteredTracks = filterResults(tracks);
        printAnalysis(filteredTracks);
    }

    /**
     * Check input parameters
     */
    protected void checkInputParams(String[] args) {
        if(args == null) {
            throw new RuntimeException(ErrorMessage.PARAMETER_REQUIRED.toString());
        }
        if(args.length !=1) {
            throw new RuntimeException(ErrorMessage.INVALID_NUM_OF_PARAMS.toString());
        }
        if(!args[0].endsWith(JSON_FILE_FORMAT)){
            throw new RuntimeException(ErrorMessage.INVALID_FILE_FORMAT.toString());
        }
    }

    /**
     * Read the JSON file
     */
    protected Map<String, Track> readJsonFile(String filename) {
        IJsonProcessing jsonProcessing = new JsonProcessing();
        return jsonProcessing.readJsonFile(filename,
                key_track,
                value_track,
                printInDebugMode);
    }

    /**
     * Read the JSON entry and return track Id
     */
    protected Function<JSONObject, Long> key_track = json -> (Long) json.get(TrackField.TRACK_ID.value());

    /**
     * Read the JSON entry and return the request Track
     */
    protected Function<JSONObject, Track> value_track = json -> {
        Track track = new Track();
        track.setKind((String) json.get(TrackField.KIND.value()));
        track.setCollectionId((Long) json.get(TrackField.COLLECTION_ID.value()));
        track.setTrackPrice(new BigDecimal(String.valueOf(json.get(TrackField.TRACK_PRICE.value()))));
        track.setTrackTimeMillis((Long) json.get(TrackField.TRACK_TIME_MILLIS.value()));
        // INFO: add new fields if required for new calculations
        return track;
    };

    /**
     * print all tracks (used in developer mode only)
     */
    protected static Consumer<Map.Entry<String, Track>> printInDebugMode = track -> {
        final Track trackValue = track.getValue();
        System.out.println("Track Details: " + String.valueOf(track.getKey()));
        System.out.println("========================================");
        System.out.println("Track collectionId is: " + trackValue.getCollectionId());
        System.out.println("Track kind is: " + trackValue.getKind());
        System.out.println("Track trackTimeMillis is: " + trackValue.getTrackTimeMillis());
        System.out.println("Track trackPrice is: " + trackValue.getTrackPrice());

        // INFO: add new fields if required for new calculations
        System.out.println();
    };

    /**
     * filter results
     */
    protected List<Track> filterResults(Map<String, Track> tracks) {
        return tracks.entrySet().stream()
                .filter(filterBy)
                .map(map -> map.getValue())
                .collect(Collectors.toList());
    }

    protected Predicate<Map.Entry<String, Track>> filterBy = map -> TrackField.SONG.value().equals(map.getValue().getKind());

    protected void printAnalysis(List<Track> tracks) {
        ICalculations calculations = new Calculations();
        long totalTime = calculations.totalTime(tracks);
        System.out.println("Analysis: Total track time: "
                + CalculationsUtil.parseTime(totalTime,TimeValue.HOURS) +" hours, "
                + CalculationsUtil.parseTime(totalTime,TimeValue.MINUTES) + " minutes, "
                + CalculationsUtil.parseTime(totalTime,TimeValue.SECONDS) + " seconds");

        System.out.println("Analysis: Number of different collections: " + calculations.numberOfDiffCollections(tracks));

        System.out.println("Analysis: Average price of a track " + calculations.averagePrice(tracks,null, null));
    }
}
