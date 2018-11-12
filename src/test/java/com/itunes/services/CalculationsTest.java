package com.itunes.services;

import com.itunes.model.Track;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CalculationsTest {

    ICalculations calculations;
    List<Track> tracks;

    @Before
    public void initialize() {
        calculations = new Calculations();
        tracks = new ArrayList<>();
        Track track1 = new Track();
        track1.setCollectionId(1L);
        track1.setTrackPrice(new BigDecimal("4.21"));
        track1.setTrackTimeMillis(4L);
        tracks.add(track1);
        Track track2 = new Track();
        track2.setCollectionId(1L);
        track2.setTrackPrice(new BigDecimal("2.24"));
        track2.setTrackTimeMillis(2L);
        tracks.add(track2);
        Track track3 = new Track();
        track3.setCollectionId(2L);
        track3.setTrackPrice(new BigDecimal("5.54"));
        track3.setTrackTimeMillis(5L);
        tracks.add(track3);
        Track track4 = new Track();
        track4.setCollectionId(3L);
        track4.setTrackPrice(new BigDecimal("9.65"));
        track4.setTrackTimeMillis(9L);
        tracks.add(track4);
    }

    @Test
    public void testNumberOfDiffCollections(){
        Assert.assertEquals(calculations.numberOfDiffCollections(tracks),3);
    }

    @Test
    public void testAveragePrice(){
        Assert.assertEquals(calculations.averagePrice(tracks, null, null), new BigDecimal("5.41"));
    }

    @Test
    public void testAveragePriceWithCustomPresicion(){
        Assert.assertEquals(calculations.averagePrice(tracks, 2, null), new BigDecimal("5.4"));
    }

    @Test
    public void testTotalTime(){
        Assert.assertEquals(calculations.totalTime(tracks), 20);
    }
}
