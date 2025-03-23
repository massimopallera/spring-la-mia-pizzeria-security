package org.lessons.java.spring_pizzeria.pizzeria_relazioni.Repository;

import org.lessons.java.spring_pizzeria.pizzeria_relazioni.Model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountRepository extends JpaRepository<Discount, Integer>{
    
}
