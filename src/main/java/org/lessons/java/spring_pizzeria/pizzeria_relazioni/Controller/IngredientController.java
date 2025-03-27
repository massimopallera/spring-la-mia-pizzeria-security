package org.lessons.java.spring_pizzeria.pizzeria_relazioni.Controller;

import java.util.NoSuchElementException;

import org.lessons.java.spring_pizzeria.pizzeria_relazioni.Model.Ingredient;
import org.lessons.java.spring_pizzeria.pizzeria_relazioni.Repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping("ingredients")
public class IngredientController {
    

    @Autowired
    IngredientRepository repo;

    @GetMapping("")
    public String index(Model model) {

        model.addAttribute("ingredients", repo.findAll());

        return "ingredients/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model) {

        
        try {
            Ingredient ingredient = repo.findById(id).get();
            model.addAttribute("ingredient", ingredient);
            
        } catch (NoSuchElementException e) {
            model.addAttribute("ingredient", false);

        }


        return "ingredients/show";
    }
    
    

}
