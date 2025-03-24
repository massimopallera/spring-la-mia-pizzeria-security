package org.lessons.java.spring_pizzeria.pizzeria_relazioni.Model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "pizze")
public class Pizza {
    
    // ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Create a new AI ID
    private Integer id;
    
    // Name
    @NotBlank
    @NotNull
    @Size(min = 5, max=40, message="Pizza name must be more than 5 characters")
    private String name;

    // Description
    @NotBlank
    @Size(min = 5, max = 200, message = "Pizza description must be between 5 and 200 characters")
    private String description;

    // url photo
    @NotBlank
    @Size( max=255, message = "URL cannot be more than 255 characters" )
    private String photoUrl;

    // Price
    @NotNull(message = "Price cannot be null")
    @Min(value = 1, message = "Price must be more than 1")
    @Max(value = 999, message = "Price must be less than 999")
    private Double price;

    @OneToMany(mappedBy = "pizza")
    private List<Discount> discounts; 
    

    @Override
    public String toString(){
        return String.format("Nome Pizza: %s\nDescrizione: %s\nPrezzo: %d", name, description, price);
    }

    /**
     * @return Integer return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return String return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return String return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return String return the photoUrl
     */
    public String getPhotoUrl() {
        return photoUrl;
    }

    /**
     * @param photoUrl the photoUrl to set
     */
    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    /**
     * @return Double return the price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(Double price) {
        this.price = price;
    }


    /**
     * @return List<Discount> return the discounts
     */
    public List<Discount> getDiscounts() {
        return discounts;
    }

    /**
     * @param discounts the discounts to set
     */
    public void setDiscounts(List<Discount> discounts) {
        this.discounts = discounts;
    }

}
