package org.antonkhmarun.enums;

import java.util.Random;

public enum Country {
    UNITED_STATES("United States");

    private final String country;

    Country(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public static Country getRandomCountry() {
        Country[] countries = Country.values();
        int randIndex = new Random().nextInt(countries.length);
        return countries[randIndex];
    }
}
