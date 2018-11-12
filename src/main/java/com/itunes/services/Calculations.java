package com.itunes.services;

import com.itunes.model.Track;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;

public class Calculations implements ICalculations {

    private static final Integer DEFAULT_PRECISION = 3;

    @Override
    public long numberOfDiffCollections(List<Track> tracks) {
        return tracks.stream()
                .map(Track::getCollectionId)
                .distinct()
                .count();
    }

    @Override
    public BigDecimal averagePrice(List<Track> tracks, Integer precision, RoundingMode roundingMode) {
        Integer averagePrecision = precision != null ? precision : DEFAULT_PRECISION;
        RoundingMode averageRoundingMode = roundingMode != null ? roundingMode : RoundingMode.HALF_UP;

        BigDecimal sum = tracks.stream()
                .map(Track::getTrackPrice)
                .map(Objects::requireNonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal average = sum.divide(new BigDecimal(tracks.size()), averageRoundingMode);

        return average.round(new MathContext(averagePrecision, averageRoundingMode));
    }

    @Override
    public long totalTime(List<Track> tracks) {
        return tracks.stream()
                .mapToLong(Track::getTrackTimeMillis)
                .sum();
    }
}
