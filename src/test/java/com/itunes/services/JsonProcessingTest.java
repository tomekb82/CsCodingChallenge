package com.itunes.services;

import com.itunes.model.Track;
import com.itunes.model.TrackField;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class JsonProcessingTest {

    @Test(expected = RuntimeException.class)
    public void shouldProcessJsonFiles() {
        IJsonProcessing jsonProcessing = new JsonProcessing();
        String filename = "test.csv";
        Function<JSONObject, Long> key_track = null;
        Function<JSONObject, Track> value_Track = null;
        jsonProcessing.readJsonFile(filename, key_track, value_Track, null);
    }

    @Test(expected = RuntimeException.class)
    public void shouldFileExist() {
        IJsonProcessing jsonProcessing = new JsonProcessing();
        String filename = "notExisting.json";
        Function<JSONObject, Long> key_track = null;
        Function<JSONObject, Track> value_Track = null;
        jsonProcessing.readJsonFile(filename, key_track, value_Track, null);
    }

    @Test(expected = RuntimeException.class)
    public void shouldHaveInputFunctionsDefined() {
        IJsonProcessing jsonProcessing = new JsonProcessing();
        String filename = "test.json";
        Function<JSONObject, Long> key_track = null;
        Function<JSONObject, Track> value_Track = null;
        jsonProcessing.readJsonFile(filename, key_track, value_Track, null);
    }

    @Test
    public void testReadJsonFile() {
        JsonProcessing jsonProcessing = new JsonProcessing();
        JsonProcessing.getFileReader = filename -> {
            ClassLoader cl = ClassLoader.getSystemClassLoader();
            File file = new File(cl.getResource(filename).getFile());
            return file;
        };
        String filename = "test.json";
        Function<JSONObject, Long> key_track = json -> (Long) json.get(TrackField.TRACK_ID.value());
        Function<JSONObject, Track> value_Track = json -> {
            Track track = new Track();
            track.setTrackId((Long) json.get(TrackField.TRACK_ID.value()));
            track.setKind((String) json.get(TrackField.KIND.value()));
            track.setCollectionId((Long) json.get(TrackField.COLLECTION_ID.value()));
            track.setTrackPrice(new BigDecimal(String.valueOf(json.get(TrackField.TRACK_PRICE.value()))));
            track.setTrackTimeMillis((Long) json.get(TrackField.TRACK_TIME_MILLIS.value()));
            return track;
        };
        Map<String, Track> testTracks = new HashMap<>();
        Track track1 = new Track();
        track1.setTrackId(1L);
        track1.setCollectionId(1L);
        track1.setTrackPrice(new BigDecimal("2.0"));
        track1.setTrackTimeMillis(200L);
        track1.setKind("song");
        testTracks.put(track1.getTrackId().toString(), track1);
        Track track2 = new Track();
        track2.setTrackId(2L);
        track2.setCollectionId(2L);
        track2.setTrackPrice(new BigDecimal("3.0"));
        track2.setTrackTimeMillis(300L);
        track2.setKind("song");
        testTracks.put(track2.getTrackId().toString(), track2);

        Map<String, Track> tracks =  jsonProcessing.readJsonFile(filename, key_track, value_Track, null);
        Assert.assertEquals(tracks.size(), 2);
        Assert.assertEquals(tracks.get(track1.getTrackId()), track1);
        Assert.assertEquals(tracks.get(track2.getTrackId()), track2);
    }
}
