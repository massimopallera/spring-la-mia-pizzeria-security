package org.lessons.java.spring_pizzeria.pizzeria_relazioni.Service;

import java.util.List;
import java.util.Optional;

import org.lessons.java.spring_pizzeria.pizzeria_relazioni.Model.Pizza;
import org.lessons.java.spring_pizzeria.pizzeria_relazioni.Repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PizzaService {
    
    @Autowired
    private PizzaRepository repository;

    public List<Pizza> getAll(){
        return repository.findAll();
    }

    public Optional<Pizza> getById(Integer id){
        Optional<Pizza> pizzaAttempt = repository.findById(id);
    
        return pizzaAttempt;
    
    }

    public Pizza save(Pizza pizza){
        repository.save(pizza);
        return pizza;
    }

    
}
