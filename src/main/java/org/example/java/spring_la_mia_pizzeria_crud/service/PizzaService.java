package org.example.java.spring_la_mia_pizzeria_crud.service;

import java.util.List;

import org.example.java.spring_la_mia_pizzeria_crud.model.Ingredient;
import org.example.java.spring_la_mia_pizzeria_crud.model.Pizza;
import org.example.java.spring_la_mia_pizzeria_crud.repo.IngredientRepository;
import org.example.java.spring_la_mia_pizzeria_crud.repo.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PizzaService {

    @Autowired
    private PizzaRepository pizzaRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    public List<Pizza> findAll() {
        return pizzaRepository.findAll();
    }

    public List<Ingredient> findIngredients() {
        return ingredientRepository.findAll();
    }

    public List<Pizza> findByName(String name) {
        return pizzaRepository.findByNameContainingIgnoreCase(name);
    }

    public Pizza findById(Integer id) {
        return pizzaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pizza con ID " + id + " non trovata"));
    }

    public Pizza create(Pizza pizza) {
        return pizzaRepository.save(pizza);
    }

    public Pizza update(Pizza pizza) {
        return pizzaRepository.save(pizza);
    }

    public void delete(Pizza pizza) {
        pizzaRepository.delete(pizza);
    }

    public void deleteById(Integer id) {
        pizzaRepository.delete(findById(id));
    }

}
