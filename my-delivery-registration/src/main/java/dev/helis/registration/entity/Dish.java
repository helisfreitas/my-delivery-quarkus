package dev.helis.registration.entity;

import java.math.BigDecimal;
import java.net.URL;

import dev.helis.registration.validation.OnlyCharacterAndPunctuation;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.smallrye.common.constraint.NotNull;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Entity
public class Dish extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @NotBlank
    @OnlyCharacterAndPunctuation
    public String name;

    @OnlyCharacterAndPunctuation
    public String description;

    @NotNull
    @Positive
    public BigDecimal price;

    public URL image;

    @Enumerated(EnumType.STRING)
    public Category category;

    public Boolean isAvailable = Boolean.TRUE;

    @ManyToOne
    public Restaurant restaurant;

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
        Dish other = (Dish) obj;
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
