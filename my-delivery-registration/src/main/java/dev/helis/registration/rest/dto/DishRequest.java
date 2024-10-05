package dev.helis.registration.rest.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import dev.helis.registration.entity.Category;
import dev.helis.registration.validation.OnlyCharacterAndPunctuation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class DishRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank
    @OnlyCharacterAndPunctuation
    private String name;

    @OnlyCharacterAndPunctuation
    private String description;

    @NotNull
    @Positive
    private BigDecimal price;

    private String image;

    @NotNull
    private Category category;

    @NotNull
    private Boolean isAvailable;

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
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

    @Override
    public String toString() {
        return "Dish [name=" + name + ", price=" + price + ", category=" + category
                + "]";
    }

}
