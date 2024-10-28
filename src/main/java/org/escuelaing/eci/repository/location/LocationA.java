package org.escuelaing.eci.repository.location;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Document(collection = "Location_collection")
public class LocationA implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private float lat;
    private float lon;
    private String address;


    public LocationA(LocationDto locationDto) {
        this.id = null;
        this.lat = locationDto.getLat();
        this.lon = locationDto.getLon();
        this.address = locationDto.getAddress();

    }

    public LocationA(String id, float lat, float lon, String address) {
        this.id = id;
        this.lat = lat;
        this.lon = lon;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void update(LocationDto locationDto) {
        this.lat = locationDto.getLat();
        this.lon = locationDto.getLon();
        this.address = locationDto.getAddress();
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", lat=" + lat +
                ", lon=" + lon +
                ", address='" + address + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocationA location = (LocationA) o;
        return Float.compare(location.lat, lat) == 0 &&
                Float.compare(location.lon, lon) == 0 &&
                Objects.equals(id, location.id) &&
                Objects.equals(address, location.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lat, lon, address);
    }
}
