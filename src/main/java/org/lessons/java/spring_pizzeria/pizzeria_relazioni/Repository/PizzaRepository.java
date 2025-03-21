package org.lessons.java.spring_pizzeria.pizzeria_relazioni.Repository;

import org.lessons.java.spring_pizzeria.pizzeria_relazioni.Model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PizzaRepository extends JpaRepository<Pizza, Integer>{
    
}
