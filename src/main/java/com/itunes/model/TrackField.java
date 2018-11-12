package com.itunes.model;

public enum TrackField {
    SONG("song"),
    TRACK_ID("trackId"),
    KIND("kind"),
    COLLECTION_ID("collectionId"),
    TRACK_PRICE("trackPrice"),
    TRACK_TIME_MILLIS("trackTimeMillis");

    private final String text;

    TrackField(final String text) {
        this.text = text;
    }

    public String value() {
        return text;
    }

}
