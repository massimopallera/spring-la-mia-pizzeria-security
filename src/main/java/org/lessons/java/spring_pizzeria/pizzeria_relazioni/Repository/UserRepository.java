package org.lessons.java.spring_pizzeria.pizzeria_relazioni.Repository;

import java.util.Optional;

import org.lessons.java.spring_pizzeria.pizzeria_relazioni.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Integer>{

    Optional<User> findByUsername(String username);

}
