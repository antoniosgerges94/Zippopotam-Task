package models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ZippopotamResponse {

    @JsonProperty("post code")
    private String postCode;

    private String country;

    @JsonProperty("country abbreviation")
    private String countryAbbreviation;

    private List<Place> places;

    public String getPostCode() {
        return postCode;
    }

    public String getCountry() {
        return country;
    }

    public String getCountryAbbreviation() {
        return countryAbbreviation;
    }

    public List<Place> getPlaces() {
        return places;
    }
}