package dev.helis.registration.rest.dto.mappers;

import dev.helis.registration.entity.Location;
import dev.helis.registration.entity.Restaurant;
import dev.helis.registration.rest.dto.RestaurantRequest;
import dev.helis.registration.rest.dto.RestaurantResponse;

public class RestaurantMapper {

    private RestaurantMapper() {
        // Private constructor to hide the implicit public one
    }

    public static RestaurantResponse mapToDto(Restaurant restaurant) {
        return new RestaurantResponse(restaurant.id, restaurant.owner, restaurant.name, restaurant.location);
    }

    public static Restaurant mapToEntity(RestaurantRequest restaurant) {
        return new Restaurant(restaurant.getName(), restaurant.getOwner(), new Location(restaurant.getLocation().getLongitude(), restaurant.getLocation().getLatitude()));
    }
}