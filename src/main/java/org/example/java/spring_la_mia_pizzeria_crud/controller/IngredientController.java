package org.example.java.spring_la_mia_pizzeria_crud.controller;

import java.util.List;

import org.example.java.spring_la_mia_pizzeria_crud.model.Ingredient;
import org.example.java.spring_la_mia_pizzeria_crud.repo.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {

    @Autowired
    private IngredientRepository repository;

    @GetMapping
    public String index(Model model) {
        List<Ingredient> ingredients = repository.findAll();
        model.addAttribute("ingredients", ingredients);
        return "ingredients/index";
    }

    @GetMapping("show/{id}")
    public String getMethodName(@PathVariable int id, Model model) {
        Ingredient ingredient = repository.findById(id).orElseThrow();

        if (ingredient != null) {
            model.addAttribute("ingredient", ingredient);

            return "ingredients/show";
        }

        return "ingredients/index";
    }

    @GetMapping("/create")
    public String getCreate(Model model) {
        model.addAttribute("ingredient", new Ingredient());
        return "ingredients/create";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("ingredient") Ingredient formIngredient, BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            return "ingredients/create";
        }

        repository.save(formIngredient);

        return "redirect:/ingredients";
    }

}
