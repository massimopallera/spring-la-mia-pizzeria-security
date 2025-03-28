package org.lessons.java.spring_pizzeria.pizzeria_relazioni.Model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table( name = "ingredients" )
public class Ingredient {
    
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Size(min = 3, message = "Name must be more than 3 chars long")
    private String name;

    // ManyToMany Relation
    @ManyToMany
    @JoinTable(
        name = "ingredient_pizza",
        joinColumns = @JoinColumn( name = "pizza_id" ),
        inverseJoinColumns = @JoinColumn( name = "ingredient_id" )
    )
    private List<Pizza> pizze;


    @Override
    public String toString(){
        return String.format("Nome: %s", name);
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
     * @return List<Pizza> return the pizze
     */
    public List<Pizza> getPizze() {
        return pizze;
    }

    /**
     * @param pizze the pizze to set
     */
    public void setPizze(List<Pizza> pizze) {
        this.pizze = pizze;
    }

}
