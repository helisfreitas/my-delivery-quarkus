package dev.helis.registration.rest;

public class ResourcePaths {

    public static final String RESTAURANTS = "/restaurants";
    public static final String DISHES = "/restaurants/{restaurantId}/dishes";

    private ResourcePaths() {
        // Prevent instantiation
    }
}
