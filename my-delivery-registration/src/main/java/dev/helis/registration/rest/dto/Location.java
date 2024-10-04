package dev.helis.registration.rest.dto;

import java.io.Serializable;

import jakarta.validation.constraints.Size;

public class Location implements Serializable {

    private static final long serialVersionUID = 1L;

    @Size(min = 11, max = 12)
    private String longitude;

    @Size(min = 11, max = 12)
    private String latitude;

    public Location() {
        // Default constructor
    }

    public Location(String longitude, String latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((longitude == null) ? 0 : longitude.hashCode());
        result = prime * result + ((latitude == null) ? 0 : latitude.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Location other = (Location) obj;
        if (longitude == null) {
            if (other.longitude != null)
                return false;
        } else if (!longitude.equals(other.longitude))
            return false;
        if (latitude == null) {
            if (other.latitude != null)
                return false;
        } else if (!latitude.equals(other.latitude))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Location [longitude=" + longitude + ", latitude=" + latitude + "]";
    }

    
}
