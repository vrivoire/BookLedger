package com.vrivoire.bookledger.model;

public enum Genre {
    SCIFI("Sci-Fi"),
    ROMANCE("Romance");

    private final String DESC;

    private Genre(String desc) {
        DESC = desc;
    }

    public String getDescription() {
        return DESC;
    }
}
