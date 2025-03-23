package org.lessons.java.spring_pizzeria.pizzeria_relazioni.Model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table( name = "discounts" )
public class Discount {
    
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer id;

    @FutureOrPresent
    // @NotBlank
    private LocalDate startDate;

    @FutureOrPresent
    // @NotBlank
    private LocalDate endDate;

    @Size(min = 5, max = 255 ,message = "Title cannot be less than 5 characters")
    @NotBlank
    private String title;

// * una pizza -> N offerte
    @ManyToOne
    @JoinColumn( name = "pizza_id", nullable=false )
    private Pizza pizza;


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
     * @return LocalDate return the startDate
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * @return LocalDate return the endDate
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    /**
     * @return String return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return Pizza return the pizza
     */
    public Pizza getPizza() {
        return pizza;
    }

    /**
     * @param pizza the pizza to set
     */
    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    @Override
    public String toString(){
        return String.format("Discount: %s", title);
    }
}
