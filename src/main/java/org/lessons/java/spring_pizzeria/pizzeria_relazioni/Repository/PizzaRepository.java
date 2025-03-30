package org.lessons.java.spring_pizzeria.pizzeria_relazioni.Repository;

import org.lessons.java.spring_pizzeria.pizzeria_relazioni.Model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;



public interface PizzaRepository extends JpaRepository<Pizza, Integer>{
    
    List<Pizza> findByNameContainingIgnoreCase(String name);

}
