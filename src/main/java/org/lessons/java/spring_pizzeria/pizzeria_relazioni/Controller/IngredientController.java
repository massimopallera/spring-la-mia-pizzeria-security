package org.lessons.java.spring_pizzeria.pizzeria_relazioni.Controller;

import java.util.NoSuchElementException;

import org.lessons.java.spring_pizzeria.pizzeria_relazioni.Model.Ingredient;
import org.lessons.java.spring_pizzeria.pizzeria_relazioni.Repository.IngredientRepository;
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
    
    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Integer id) {

        try{
            model.addAttribute("ingredient", repo.findById(id).get());
        } catch (NoSuchElementException e){
            model.addAttribute("ingredient", false);
        }
        
        return "ingredients/edit";
    }

    @PostMapping("/edit/{id}")
    public String store(@Valid @ModelAttribute Ingredient ingredient, BindingResult br) {

        if (br.hasErrors()) {
            return "ingredients/edit";
        }

        repo.save(ingredient);
            
        return "redirect:/ingredients";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("ingredient", new Ingredient());
        
        return "ingredients/create";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute Ingredient ingredient, BindingResult br) {

        if (br.hasErrors()) {
            return "ingredients/create";
        }

        repo.save(ingredient);
            
        return "redirect:/ingredients";
    }
    
    
    

}
