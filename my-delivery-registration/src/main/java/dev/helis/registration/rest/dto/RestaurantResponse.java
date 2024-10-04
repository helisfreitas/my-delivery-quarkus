package dev.helis.registration.rest.dto;

import java.util.Objects;

import jakarta.validation.constraints.NotBlank;

public class RestaurantResponse implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String owner;

    private String name;

    private Location location;
  
    public RestaurantResponse(Long id, @NotBlank String owner, @NotBlank String name,
            dev.helis.registration.entity.Location location) {         
          this.id = id;
          this.owner = owner;
          this.name = name;
          if(Objects.nonNull(location)) {
             this.location = new Location(location.convertLongitudeToDMS(), location.convertLatitudeToDMS());
          }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((owner == null) ? 0 : owner.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        RestaurantResponse other = (RestaurantResponse) obj;
        if (owner == null) {
            if (other.owner != null)
                return false;
        } else if (!owner.equals(other.owner))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

}
