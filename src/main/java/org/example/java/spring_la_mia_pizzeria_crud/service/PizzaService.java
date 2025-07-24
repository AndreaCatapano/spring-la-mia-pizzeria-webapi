package org.example.java.spring_la_mia_pizzeria_crud.service;

import java.util.List;

import org.example.java.spring_la_mia_pizzeria_crud.model.Pizza;
import org.example.java.spring_la_mia_pizzeria_crud.repo.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PizzaService {

    @Autowired
    private PizzaRepository pizzaRepository;

    public List<Pizza> findAll() {
        List<Pizza> pizzas = pizzaRepository.findAll();
        return pizzas;
    }

    public List<Pizza> findByName(String name) {
        List<Pizza> pizzas = pizzaRepository.findByNameContainingIgnoreCase(name);
        return pizzas;
    }

    public Pizza findById(Integer id) {
        return pizzaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pizza con ID " + id + " non trovata"));
    }

    public void create(Pizza pizza) {
        pizzaRepository.save(pizza);
    }

    public void update(Pizza pizza) {
        pizzaRepository.save(pizza);
    }

    public void delete(Pizza pizza) {
        pizzaRepository.delete(pizza);
    }

    public void deleteById(Integer id) {
        pizzaRepository.delete(findById(id));
    }

}
