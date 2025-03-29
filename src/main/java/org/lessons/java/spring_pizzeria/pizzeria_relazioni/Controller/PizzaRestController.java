package org.lessons.java.spring_pizzeria.pizzeria_relazioni.Controller;

import java.util.List;
import java.util.Optional;

import javax.naming.Binding;

import org.lessons.java.spring_pizzeria.pizzeria_relazioni.Model.Pizza;
import org.lessons.java.spring_pizzeria.pizzeria_relazioni.Service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/api/pizze")
public class PizzaRestController {
    
    @Autowired
    PizzaService service;

    @GetMapping
    public List<Pizza> index() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pizza> show(@PathVariable Integer id) {

        Optional<Pizza> result = service.getById(id);

        if (result.isPresent()) {
            return new ResponseEntity<Pizza>(result.get(), HttpStatus.OK);
        }

        return new ResponseEntity<Pizza>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Pizza> create(@Valid @RequestBody Pizza pizza, BindingResult br) {
        
        if(br.hasErrors()){
            return new ResponseEntity<Pizza>(HttpStatus.BAD_REQUEST);
        }

        service.save(pizza);
        return new ResponseEntity<Pizza>(pizza, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pizza> update(@RequestBody Pizza pizza, @PathVariable Integer id) {
        
        // if(br.hasErrors()){
        //     return new ResponseEntity<Pizza>(HttpStatus.BAD_REQUEST);
        // }

        pizza.setId(id);
        service.save(pizza);

        return new ResponseEntity<Pizza>(pizza, HttpStatus.OK);

    }
    
    
    

}
