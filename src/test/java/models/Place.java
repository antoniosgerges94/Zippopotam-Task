package models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Place {

    @JsonProperty("place name")
    private String placeName;

    private String longitude;

    private String latitude;

    private String state;

    @JsonProperty("state abbreviation")
    private String stateAbbreviation;

    public String getPlaceName() {
        return placeName;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getState() {
        return state;
    }

    public String getStateAbbreviation() {
        return stateAbbreviation;
    }
}