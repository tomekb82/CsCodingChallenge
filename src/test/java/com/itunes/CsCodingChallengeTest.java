package com.itunes;
import com.itunes.model.Track;
import com.itunes.services.JsonProcessing;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class CsCodingChallengeTest {

    CsCodingChallenge csCodingChallenge;

    @Before
    public void initialize() {
        csCodingChallenge = new CsCodingChallenge();
    }

    @Test(expected = RuntimeException.class)
    public void shouldTakeNotNullInputParams(){
        String[] empty = null;
        csCodingChallenge.process(empty);
    }

    @Test(expected = RuntimeException.class)
    public void shouldTakeSingleInputParam(){
        String[] empty = new String[0];
        csCodingChallenge.process(empty);
    }

    @Test(expected = RuntimeException.class)
    public void shouldTakeJsonFileAsInputParam(){
        String[] wrongFile = new String[1];
        wrongFile[0] = "test";
        csCodingChallenge.process(wrongFile);
    }

    @Test
    public void shouldFilterResults(){
        Map<String, Track> tracks = new HashMap<>();
        Track track1 = new Track();
        track1.setTrackId(1L);
        track1.setKind("song");
        tracks.put(track1.getTrackId().toString(), track1);
        Track track2 = new Track();
        track2.setTrackId(2L);
        track2.setKind("wrong");
        tracks.put(track2.getTrackId().toString(), track2);
        Assert.assertEquals(csCodingChallenge.filterResults(tracks).size(), 1);
    }

    @Test
    public void shouldFilterResultsUsingCustomCondition(){
        Map<String, Track> tracks = new HashMap<>();
        Track track1 = new Track();
        track1.setTrackId(1L);
        track1.setKind("song");
        tracks.put(track1.getTrackId().toString(), track1);
        Track track2 = new Track();
        track2.setTrackId(2L);
        track2.setKind("wrong");
        tracks.put(track2.getTrackId().toString(), track2);
        Track track3 = new Track();
        track3.setTrackId(10L);
        track3.setKind("wrong");
        tracks.put(track3.getTrackId().toString(), track3);
        csCodingChallenge.filterBy = map -> Long.valueOf(map.getKey()).longValue() > 9;
        Assert.assertEquals(csCodingChallenge.filterResults(tracks).size() ,1);
    }

    @Test // INFO: Test for run purpose only
    public void shouldPrintAnalysis(){
        JsonProcessing.getFileReader = filename -> {
            ClassLoader cl = ClassLoader.getSystemClassLoader();
            File file = new File(cl.getResource(filename).getFile());
            return file;
        };
        String[] filename = new String[1];
        filename[0] = "itunes.json";
        csCodingChallenge.process(filename);
    }


}
