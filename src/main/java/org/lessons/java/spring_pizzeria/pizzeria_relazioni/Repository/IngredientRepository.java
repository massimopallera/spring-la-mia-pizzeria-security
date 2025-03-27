package org.lessons.java.spring_pizzeria.pizzeria_relazioni.Repository;

import org.lessons.java.spring_pizzeria.pizzeria_relazioni.Model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer>{
    
}
