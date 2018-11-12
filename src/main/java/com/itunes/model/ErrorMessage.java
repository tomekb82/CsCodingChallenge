package com.itunes.model;

public enum ErrorMessage {

    PARAMETER_REQUIRED("Input parameter required"),
    INVALID_NUM_OF_PARAMS("Invalid number of parameters"),
    INVALID_FILE_FORMAT("Invalid file format"),
    FILE_NOT_EXIST("File doesn't exists");

    private final String text;

    ErrorMessage(final String text) {
        this.text = text;
    }

    public String value() {
        return text;
    }
}
