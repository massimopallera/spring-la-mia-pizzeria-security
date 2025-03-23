package org.lessons.java.spring_pizzeria.pizzeria_relazioni.Controller;

import java.util.NoSuchElementException;
import org.lessons.java.spring_pizzeria.pizzeria_relazioni.Model.Discount;
import org.lessons.java.spring_pizzeria.pizzeria_relazioni.Model.Pizza;
import org.lessons.java.spring_pizzeria.pizzeria_relazioni.Repository.DiscountRepository;
import org.lessons.java.spring_pizzeria.pizzeria_relazioni.Repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RequestMapping("/discounts")
public class DiscountController {

    @Autowired
    private DiscountRepository discountRepo;

    // * ID is for pizza
    @GetMapping("/create")
    public String create(Model model, @RequestParam(required = true) Integer id) {
        
        model.addAttribute("discount", new Discount());
        // model.addAttribute("pizzaId", (id));

        return "discounts/create";
    }
    
    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("discount") Discount discountForm, BindingResult br, Model model, @RequestParam(required = true) Integer id) {        
        
        if(br.hasErrors()){
            model.addAttribute("discount", discountForm);
            return "discounts/create?id=" + model.getAttribute("pizzaId");
        }

        Pizza pizzaId = new Pizza();
        pizzaId.setId(id);

        discountForm.setPizza( pizzaId );
        
        discountRepo.save(discountForm);

        // save pizzaId in discountForm



        return "redirect:/pizze/" + discountForm.getPizza().getId();
    }
    

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {

        try{
            Discount discount = discountRepo.findById(id).get();
            model.addAttribute("discount", discount);

        } catch(NoSuchElementException e){
            model.addAttribute("discount", false);
        }

        return "discounts/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("discount") Discount discountForm,BindingResult br, Model model) {
        
        if(br.hasErrors()){
            model.addAttribute("discount", discountForm);
            return "discounts/edit";
        }

        discountRepo.save(discountForm);
        

        return "redirect:/pizze/" + discountForm.getPizza().getId();
    }
    
    
    
}
