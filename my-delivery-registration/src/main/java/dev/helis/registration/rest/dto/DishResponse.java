package dev.helis.registration.rest.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.net.URL;

import dev.helis.registration.entity.Category;


public class DishResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private String description;

    private BigDecimal price;

    private URL image;

    private Category category;

    private Boolean isAvailable;

    private Long restaurant;
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public URL getImage() {
        return image;
    }

    public void setImage(URL image) {
        this.image = image;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public Long getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Long restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((category == null) ? 0 : category.hashCode());
        result = prime * result + ((restaurant == null) ? 0 : restaurant.hashCode());
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
        DishResponse other = (DishResponse) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (category != other.category)
            return false;
        if (restaurant == null) {
            if (other.restaurant != null)
                return false;
        } else if (!restaurant.equals(other.restaurant))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Dish [name=" + name + ", price=" + price + ", category=" + category + ", restaurant=" + restaurant
                + "]";
    }    

}
