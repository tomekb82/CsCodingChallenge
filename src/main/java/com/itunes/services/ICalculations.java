package com.itunes.services;

import com.itunes.model.Track;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public interface ICalculations {

    long numberOfDiffCollections(List<Track> tracks);
    public BigDecimal averagePrice(List<Track> tracks, Integer precision, RoundingMode roundingMode);
    long totalTime(List<Track> tracks);
}
