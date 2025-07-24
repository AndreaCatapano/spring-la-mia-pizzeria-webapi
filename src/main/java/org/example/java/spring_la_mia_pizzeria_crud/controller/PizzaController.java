package org.example.java.spring_la_mia_pizzeria_crud.controller;

import java.util.List;

import org.example.java.spring_la_mia_pizzeria_crud.model.Ingredient;
import org.example.java.spring_la_mia_pizzeria_crud.model.Pizza;
import org.example.java.spring_la_mia_pizzeria_crud.repo.IngredientRepository;
import org.example.java.spring_la_mia_pizzeria_crud.service.PizzaService;
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

@Controller
@RequestMapping("/pizzas")
public class PizzaController {

    @Autowired
    private PizzaService pizzaService;

    @Autowired
    private IngredientRepository ingredientRepository;

    @GetMapping
    public String index(@RequestParam(required = false) String name, Model model) {
        List<Pizza> pizzas;

        if (name != null && !name.isEmpty()) {
            pizzas = pizzaService.findByName(name);
        } else {
            pizzas = pizzaService.findAll();
        }

        model.addAttribute("pizzas", pizzas);
        return "/pizzas/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable int id, Model model) {
        Pizza pizza = pizzaService.findById(id);

        if (pizza != null) {
            model.addAttribute("pizzas", pizza);
            return "/pizzas/show";
        }

        return "/pizzas/index";
    }

    @GetMapping("/create")
    public String getCreate(Model model) {
        model.addAttribute("pizza", new Pizza());
        List<Ingredient> availableIngredients = ingredientRepository.findAll();
        model.addAttribute("availableIngredients", availableIngredients);

        return "/pizzas/create";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "/pizzas/create";
        }

        pizzaService.create(formPizza);

        return "redirect:/pizzas";
    }

    @GetMapping("/update/{id}")
    public String getUpdate(@PathVariable("id") Integer id, Model model) {
        List<Ingredient> availableIngredients = ingredientRepository.findAll();

        model.addAttribute("pizza", pizzaService.findById(id));
        model.addAttribute("availableIngredients", availableIngredients);
        return "/pizzas/update";
    }

    @PostMapping("/update/{id}")
    public String update(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            List<Ingredient> availableIngredients = ingredientRepository.findAll();
            model.addAttribute("availableIngredients", availableIngredients);
            return "/pizzas/update";
        }

        pizzaService.update(formPizza);

        return "redirect:/pizzas";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {

        pizzaService.deleteById(id);
        return "redirect:/pizzas";
    }

}
