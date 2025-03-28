package org.lessons.java.spring_pizzeria.pizzeria_relazioni.Controller;

import java.util.NoSuchElementException;

import org.lessons.java.spring_pizzeria.pizzeria_relazioni.Model.Ingredient;
import org.lessons.java.spring_pizzeria.pizzeria_relazioni.Model.Pizza;
import org.lessons.java.spring_pizzeria.pizzeria_relazioni.Repository.IngredientRepository;
import org.lessons.java.spring_pizzeria.pizzeria_relazioni.Repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@Controller
@RequestMapping("ingredients")
public class IngredientController {
    

    @Autowired
    IngredientRepository repo;

    @Autowired
    PizzaRepository pizzaRepo;

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

            for (Pizza pizza : ingredient.getPizze()) {
                System.out.println(pizza.getName());
            }
            
        } catch (NoSuchElementException e) {
            model.addAttribute("ingredient", false);

        }


        return "ingredients/show";
    }
    
    
    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("ingredient", new Ingredient());
        model.addAttribute("pizze", pizzaRepo.findAll());
        
        return "ingredients/create";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute Ingredient ingredient, BindingResult br, Model model) {

        if (br.hasErrors()) {
            model.addAttribute("pizze", pizzaRepo.findAll());
            return "ingredients/create";
        }

        repo.save(ingredient);
            
        return "redirect:/ingredients";
    }
    
    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Integer id) {

        try{
            model.addAttribute("ingredient", repo.findById(id).get());
            model.addAttribute("pizze", pizzaRepo.findAll());
        } catch (NoSuchElementException e){
            model.addAttribute("ingredient", false);
        }
        
        return "ingredients/edit";
    }

    @PostMapping("/edit/{id}")
    public String store(@Valid @ModelAttribute Ingredient ingredient, BindingResult br, Model model) {

        if (br.hasErrors()) {
            model.addAttribute("pizze", pizzaRepo.findAll());
            return "ingredients/edit";
        }

        repo.save(ingredient);
            
        return "redirect:/ingredients";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        
        Ingredient toDelete = repo.findById(id).get();

        for (Pizza pizza : toDelete.getPizze()) {
            pizza.getIngredients().remove(toDelete);
        }

        repo.delete(toDelete);

        return "redirect:/ingredients";

    }
    

}
