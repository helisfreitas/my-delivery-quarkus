package dev.helis.registration.rest;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import dev.helis.registration.entity.Restaurant;
import dev.helis.registration.rest.dto.RestaurantRequest;
import dev.helis.registration.rest.dto.RestaurantResponse;
import dev.helis.registration.rest.dto.mappers.RestaurantMapper;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path(ResourcePaths.RESTAURANTS)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestaurantResources {

    @GET
     public List<RestaurantResponse> findAll() {
        return Restaurant.findAll().stream().map( r -> (Restaurant)r).map(RestaurantMapper::mapToDto).collect(Collectors.toList());
    }

    @GET
    @Path("/{id}")
    public RestaurantResponse findById(Long id) {
        return Restaurant.findByIdOptional(id).map( r -> (Restaurant)r).map(RestaurantMapper::mapToDto).orElseThrow(NotFoundException::new);
    }

    @POST
    @Transactional
    public Response create(@Valid RestaurantRequest restaurant) {
        Restaurant mapToEntity = RestaurantMapper.mapToEntity(restaurant);
        Restaurant.persist(mapToEntity);
        return Response.status(HttpServletResponse.SC_CREATED).location(URI.create(ResourcePaths.RESTAURANTS + "/" + mapToEntity.id)).build();
    }


    @PUT
    @Path("/{id}")
    @Transactional
    public void update(Long id, @Valid RestaurantRequest updatedRestaurant) {
        Optional<Restaurant> optional  = Restaurant.findByIdOptional(id);
        if (!optional.isPresent()) {
            throw new NotFoundException();
        }
        Restaurant restaurant = optional.get();
        restaurant.name = updatedRestaurant.getName();
        restaurant.location.setLatitude(updatedRestaurant.getLocation().getLatitude());
        restaurant.location.setLongitude(updatedRestaurant.getLocation().getLongitude());

        restaurant.persist();
    }

    @DELETE
    @Path("/{id}")
    public void delete(Long id) {

        Optional<Restaurant> optional  = Restaurant.findByIdOptional(id);
        optional.ifPresentOrElse(PanacheEntityBase::delete, () -> {throw new NotFoundException();});
    }


}
