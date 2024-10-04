package dev.helis.registration.rest;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import dev.helis.registration.entity.Dish;
import dev.helis.registration.entity.Restaurant;
import dev.helis.registration.rest.dto.DishRequest;
import dev.helis.registration.rest.dto.DishResponse;
import dev.helis.registration.rest.dto.mappers.DishMapper;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilderException;

@Path(ResourcePaths.DISHES)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DishResources {

    private static final String SELECT_FROM_DISH_WHERE_RESTAURANT_ID_AND_ID = "restaurant.id = ?1 and id = ?2";

    @GET
    public List<DishResponse> findAll(@PathParam("restaurantId") Long restaurantId) {
        return Dish.list("restaurant.id", restaurantId).stream().map( r -> (Dish)r).map(DishMapper::mapToDto).collect(Collectors.toList());
    }

    @GET
    @Path("/{id}")
    public DishResponse findById(@PathParam("restaurantId") Long restaurantId, @PathParam("id") Long id) {
      return Dish.find(SELECT_FROM_DISH_WHERE_RESTAURANT_ID_AND_ID, restaurantId, id).singleResultOptional().map( r -> (Dish)r).map(DishMapper::mapToDto).orElseThrow(NotFoundException::new);
    }

    @POST
    @Transactional
    public Response create(@PathParam("restaurantId") Long restaurantId, @Valid DishRequest request) throws MalformedURLException, IllegalArgumentException, UriBuilderException {
       
        Optional<Restaurant> restaurant = Restaurant.findByIdOptional(restaurantId);
        if (!restaurant.isPresent()) {
            throw new NotFoundException();
        }

        Dish mapToEntity = DishMapper.mapToEntity(restaurant.get(),request);
        mapToEntity.restaurant = restaurant.get();
        mapToEntity.persist();

        return Response.status(Response.Status.CREATED).location(URI.create(ResourcePaths.DISHES.replace("{restaurantId}", restaurantId.toString()) + "/" + mapToEntity.id)).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response update(@PathParam("restaurantId") Long restaurantId, @PathParam("id") Long id, @Valid DishRequest request) throws MalformedURLException, IllegalArgumentException, UriBuilderException {
        Optional<Dish> optional  = Dish.find(SELECT_FROM_DISH_WHERE_RESTAURANT_ID_AND_ID, restaurantId, id).singleResultOptional();
        if (!optional.isPresent()) {
            throw new NotFoundException();
        }
        Dish dish = optional.get();
        dish.name = request.getName();
        dish.description = request.getDescription();
        dish.price = request.getPrice();
        dish.image = null;
        dish.category = request.getCategory();
        dish.isAvailable = request.getIsAvailable();
        dish.persist();
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(@PathParam("restaurantId") Long restaurantId, @PathParam("id") Long id) {
        Optional<Dish> optional  = Dish.find(SELECT_FROM_DISH_WHERE_RESTAURANT_ID_AND_ID, restaurantId, id).singleResultOptional();
        if (!optional.isPresent()) {
            throw new NotFoundException();
        }
        optional.get().delete();
    }
}
