package org.example.java.spring_la_mia_pizzeria_crud.controller;

import org.example.java.spring_la_mia_pizzeria_crud.model.Pizza;
import org.example.java.spring_la_mia_pizzeria_crud.model.Promotion;
import org.example.java.spring_la_mia_pizzeria_crud.repo.PizzaRepository;
import org.example.java.spring_la_mia_pizzeria_crud.repo.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
@RequestMapping("promotions")
public class PromotionController {

    @Autowired
    private PromotionRepository promotionRepository;

    @Autowired
    private PizzaRepository pizzaRepository;

    @GetMapping("/create")
    public String getCreate(@RequestParam("pizzaId") Integer pizzaId, Model model) {

        Pizza pizza = pizzaRepository.findById(pizzaId).orElseThrow();

        Promotion promotion = new Promotion();
        promotion.setPizza(pizza);

        model.addAttribute("promotion", promotion);
        model.addAttribute("pizza", pizza);

        return "promotions/create";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("pizza") Promotion formPromotion,
            @RequestParam("pizzaId") Integer pizzaId, BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            Pizza pizza = pizzaRepository.findById(pizzaId).orElseThrow();
            model.addAttribute("pizza", pizza);
            return "promotions/create";
        }

        Pizza pizza = pizzaRepository.findById(pizzaId).orElseThrow();
        formPromotion.setPizza(pizza);

        promotionRepository.save(formPromotion);

        return "redirect:/pizzas/" + pizzaId;
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        Promotion promotion = promotionRepository.findById(id).orElseThrow();

        model.addAttribute("promotion", promotion);
        model.addAttribute("pizza", promotion.getPizza()); // Per mostrare info pizza

        return "promotions/edit";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable int id,
            @Valid @ModelAttribute("promotion") Promotion formPromotion,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            Promotion existingPromotion = promotionRepository.findById(id).orElseThrow();
            model.addAttribute("pizza", existingPromotion.getPizza());
            return "promotions/edit";
        }

        Promotion existingPromotion = promotionRepository.findById(id).orElseThrow();

        existingPromotion.setName(formPromotion.getName());
        existingPromotion.setStartDate(formPromotion.getStartDate());
        existingPromotion.setEndDate(formPromotion.getEndDate());

        promotionRepository.save(existingPromotion);

        return "redirect:/pizzas/" + existingPromotion.getPizza().getId();
    }

    @PostMapping("/delete/{id}")
    public String confirmDelete(@PathVariable int id) {
        Promotion promotion = promotionRepository.findById(id).orElseThrow();
        int pizzaId = promotion.getPizza().getId();

        promotionRepository.deleteById(id);

        return "redirect:/pizzas/" + pizzaId;

    }
}
