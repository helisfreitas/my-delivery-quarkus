package dev.helis.registration.rest.dto.mappers;

import java.net.MalformedURLException;

import dev.helis.registration.entity.Dish;
import dev.helis.registration.entity.Restaurant;
import dev.helis.registration.rest.dto.DishRequest;
import dev.helis.registration.rest.dto.DishResponse;
import jakarta.ws.rs.core.UriBuilderException;

public class DishMapper {


    private DishMapper() {
        // Private constructor to hide the implicit public one
    }

    public static DishResponse mapToDto(Dish entity) {
        DishResponse dish = new DishResponse();

        dish.setId(entity.id);
        dish.setName(entity.name);
        dish.setDescription(entity.description);
        dish.setPrice(entity.price);
        dish.setImage(entity.image);
        dish.setCategory(entity.category);
        dish.setIsAvailable(entity.isAvailable);
        dish.setRestaurant(entity.restaurant.id);


        return dish;
    }

    public static Dish mapToEntity(Restaurant restaurant, DishRequest dto) throws MalformedURLException, IllegalArgumentException, UriBuilderException {

        Dish dish = new Dish();
        dish.name = dto.getName();
        dish.description = dto.getDescription();
        dish.price = dto.getPrice();
        dish.image = null;
        dish.category = dto.getCategory();
        dish.isAvailable = dto.getIsAvailable();
        dish.restaurant = restaurant;

        return dish;
    }

}
