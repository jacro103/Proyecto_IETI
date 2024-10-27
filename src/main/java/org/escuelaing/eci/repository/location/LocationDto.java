package org.escuelaing.eci.repository.location;

public class LocationDto {
    private final float lat;
    private final float lon;
    private final String address;

    public LocationDto(float lat, float lon, String address) {
        this.lat = lat;
        this.lon = lon;
        this.address = address;
    }

    public float getLat() {
        return lat;
    }

    public float getLon() {
        return lon;
    }

    public String getAddress() {
        return address;
    }
}
