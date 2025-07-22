package org.example.java.spring_la_mia_pizzeria_crud.controller;

import java.util.List;

import org.example.java.spring_la_mia_pizzeria_crud.model.Ingredient;
import org.example.java.spring_la_mia_pizzeria_crud.repo.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/ingredient")
public class IngredientController {

    @Autowired
    private IngredientRepository repository;

    @GetMapping
    public String index(Model model) {
        List<Ingredient> ingredients = repository.findAll();
        model.addAttribute("ingredients", ingredients);
        return "ingredient/index";
    }

}
