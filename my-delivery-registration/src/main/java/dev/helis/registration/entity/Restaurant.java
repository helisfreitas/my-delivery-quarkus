package dev.helis.registration.entity;

import java.time.LocalDate;

import dev.helis.registration.validation.OnlyCharacterAndPunctuation;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;

@Entity
public class Restaurant extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @NotBlank
    public String owner;

    @NotBlank
    @OnlyCharacterAndPunctuation
    public String name;

    @ManyToOne(cascade = CascadeType.ALL)
    public Location location;

    @PastOrPresent
    public LocalDate creationDate;

    @PastOrPresent
    public LocalDate lastUpdate;

    public Restaurant() {

        // Default constructor
    }

    public Restaurant(String name, String owner, Location location) {
        this.owner = owner;
        this.name = name;
        this.location = location;
        this.creationDate = LocalDate.now();
        this.lastUpdate = LocalDate.now();
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
        Restaurant other = (Restaurant) obj;
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

    @Override
    public String toString() {
        return "Restaurant [owner=" + owner + ", name=" + name + "]";
    }

}
