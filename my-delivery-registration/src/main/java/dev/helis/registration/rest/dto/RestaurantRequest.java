package dev.helis.registration.rest.dto;

import dev.helis.registration.validation.OnlyCharacterAndPunctuation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class RestaurantRequest implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank
    @Size(min = 5, max = 100)
    private String owner;

    @NotBlank
    @Size(min = 5, max = 100)
    @OnlyCharacterAndPunctuation
    private String name;

    @NotNull  
    private Location location;

    public RestaurantRequest() {
        // Default constructor
    }

    public RestaurantRequest(@NotBlank String owner, @NotBlank String name, @NotNull Location location) {
        this.owner = owner;
        this.name = name;
        this.location = location;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "RestaurantRequest [owner=" + owner + ", name=" + name + ", location=" + location + "]";
    }   

}
